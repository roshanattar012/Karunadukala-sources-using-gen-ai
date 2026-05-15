package com.karunada.kala.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.karunada.kala.data.*
import com.karunada.kala.data.local.HeritageDao
import com.karunada.kala.data.local.entities.*
import com.karunada.kala.data.local.toDomain
import com.karunada.kala.data.local.toEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeritageRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val heritageDao: HeritageDao
) {
    // Heritage Sites (Artisans, Workshops as markers)
    fun getLocalHeritageData(): Flow<List<HeritageSite>> = 
        heritageDao.getAllSites().map { entities -> entities.map { it.toDomain() } }

    // Art Forms
    fun getLocalArtForms(): Flow<List<ArtForm>> =
        heritageDao.getAllArtForms().map { entities -> entities.map { it.toDomain() } }

    // Events
    fun getLocalEvents(): Flow<List<ArtEvent>> =
        heritageDao.getAllEvents().map { entities -> entities.map { it.toDomain() } }

    // Products
    fun getLocalProducts(): Flow<List<Product>> =
        heritageDao.getAllProducts().map { entities -> entities.map { it.toDomain() } }

    // Favorites
    fun getSavedItems(): Flow<List<SavedEntity>> = heritageDao.getSavedItems()

    // Bookings
    fun getAllBookings(): Flow<List<BookingEntity>> = heritageDao.getAllBookings()

    suspend fun toggleSave(id: String, type: String, isSaved: Boolean) {
        val userId = firebaseAuth.currentUser?.uid
        if (isSaved) {
            heritageDao.unsaveItem(id)
            if (userId != null) {
                try {
                    firestore.collection("users").document(userId)
                        .collection("saved_items").document(id)
                        .delete().await()
                } catch (e: Exception) { }
            }
        } else {
            val entity = SavedEntity(id, type)
            heritageDao.saveItem(entity)
            if (userId != null) {
                try {
                    firestore.collection("users").document(userId)
                        .collection("saved_items").document(id)
                        .set(mapOf("type" to type, "timestamp" to System.currentTimeMillis()))
                        .await()
                } catch (e: Exception) { }
            }
        }
    }

    suspend fun addBooking(booking: BookingEntity) {
        heritageDao.insertBooking(booking)
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            try {
                firestore.collection("users").document(userId)
                    .collection("bookings")
                    .document(booking.id)
                    .set(booking)
                    .await()
            } catch (e: Exception) { }
        }
    }

    fun isItemSaved(id: String): Flow<Boolean> = heritageDao.isItemSaved(id)

    fun startRealtimeSync() {
        // Observe artisans collection
        firestore.collection("artisans").addSnapshotListener { snapshot, _ ->
            snapshot?.let {
                val sites = it.documents.mapNotNull { doc -> 
                    try {
                        doc.toObject(HeritageSite::class.java)?.copy(id = doc.id, type = "ARTISAN") 
                    } catch (e: Exception) { null }
                }
                if (sites.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        heritageDao.insertSites(sites.map { s -> s.toEntity() })
                    }
                }
            }
        }

        // Observe art_forms collection
        firestore.collection("art_forms").addSnapshotListener { snapshot, _ ->
            snapshot?.let {
                val forms = it.documents.mapNotNull { doc -> 
                    try {
                        doc.toObject(ArtForm::class.java)?.copy(id = doc.id) 
                    } catch (e: Exception) { null }
                }
                if (forms.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        heritageDao.insertArtForms(forms.map { it.toEntity() })
                    }
                }
            }
        }

        // Observe events collection
        firestore.collection("events").addSnapshotListener { snapshot, _ ->
            snapshot?.let {
                val events = it.documents.mapNotNull { doc -> 
                    try {
                        doc.toObject(ArtEvent::class.java)?.copy(id = doc.id) 
                    } catch (e: Exception) { null }
                }
                if (events.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        heritageDao.insertEvents(events.map { it.toEntity() })
                    }
                }
            }
        }

        // Observe products collection
        firestore.collection("products").addSnapshotListener { snapshot, _ ->
            snapshot?.let {
                val products = it.documents.mapNotNull { doc -> 
                    try {
                        doc.toObject(Product::class.java)?.copy(id = doc.id) 
                    } catch (e: Exception) { null }
                }
                if (products.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        heritageDao.insertProducts(products.map { it.toEntity() })
                    }
                }
            }
        }

        // Real-time sync for User Data (Saved Items & Bookings)
        firebaseAuth.addAuthStateListener { auth ->
            val userId = auth.currentUser?.uid
            if (userId != null) {
                // Listen for Saved Items
                firestore.collection("users").document(userId)
                    .collection("saved_items").addSnapshotListener { snapshot, _ ->
                        snapshot?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                it.documents.forEach { doc ->
                                    val type = doc.getString("type") ?: "ARTISAN"
                                    heritageDao.saveItem(SavedEntity(doc.id, type))
                                }
                            }
                        }
                    }
                
                // Listen for Bookings
                firestore.collection("users").document(userId)
                    .collection("bookings").addSnapshotListener { snapshot, _ ->
                        snapshot?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                it.documents.forEach { doc ->
                                    try {
                                        val booking = doc.toObject(BookingEntity::class.java)?.copy(id = doc.id)
                                        if (booking != null) {
                                            heritageDao.insertBooking(booking)
                                        }
                                    } catch (e: Exception) { }
                                }
                            }
                        }
                    }
            }
        }
    }

    suspend fun refreshAllData() {
        // Step 1: Immediately populate with mock data if needed so user sees content right away
        checkAndPopulateMockData()

        // Step 2: Then try to refresh from Firestore in background
        coroutineScope {
            val sites = async { try { refreshHeritageSites() } catch (e: Exception) { } }
            val forms = async { try { refreshArtForms() } catch (e: Exception) { } }
            val events = async { try { refreshEvents() } catch (e: Exception) { } }
            val products = async { try { refreshProducts() } catch (e: Exception) { } }
            
            // Wait for all to finish, but they are async so they won't block each other
            listOf(sites, forms, events, products).awaitAll()
        }
    }

    private suspend fun checkAndPopulateMockData() {
        // Always try to insert mock data to ensure new items are added
        // (REPLACE strategy will prevent duplicates)
        heritageDao.insertSites(MockData.artisans.map { it.toHeritageSite().toEntity() })
        heritageDao.insertArtForms(MockData.artForms.map { it.toEntity() })
        heritageDao.insertEvents(MockData.events.map { it.toEntity() })
        heritageDao.insertProducts(MockData.products.map { it.toEntity() })
    }

    // Helper to convert Artisan to HeritageSite for the map
    private fun Artisan.toHeritageSite() = HeritageSite(
        id = id,
        name = name,
        type = type.name,
        artForm = artForm,
        district = district,
        imageUrl = imageUrl,
        description = "Authentic $artForm expert from $district. Tap to connect and learn the traditional craft.",
        latitude = when(id) {
            "p1" -> 13.3409 // Udupi
            "p2" -> 13.3392 // Tumkur
            "p3" -> 14.6221 // Sirsi
            "p4" -> 13.9299 // Shimoga
            "p5" -> 12.9141 // Mangaluru
            "p6" -> 14.2811 // Honnavar
            "p7" -> 12.5218 // Mandya
            "p8" -> 13.3409 // Udupi
            "p9" -> 12.6518 // Channapatna
            "p10" -> 13.3161 // Chikmagalur
            "p11" -> 13.2127 // Karkala
            "p12" -> 14.7937 // Haveri
            "p13" -> 13.3392 // Tumkur
            "p14" -> 14.8090 // Karwar
            "p15" -> 13.6247 // Kundapur
            "p16" -> 13.0070 // Hassan
            "p17" -> 14.4371 // Kumta
            "p18" -> 13.3409 // Udupi
            "p19" -> 15.4589 // Dharwad
            "p20" -> 12.2958 // Mysuru
            "p21" -> 12.8913 // Bantwal
            "p22" -> 15.4313 // Gadag
            "p23" -> 12.4244 // Kodagu
            "p24" -> 12.7656 // Puttur
            "p25" -> 14.2300 // Chitradurga
            "p26" -> 12.5593 // Sullia
            "p27" -> 13.7935 // Byndoor
            "p28" -> 16.1817 // Bagalkot
            "p29" -> 11.9261 // Chamarajanagar
            "p30" -> 14.9643 // Yellapur
            "w1" -> 15.3483 // Koppal
            "w2" -> 17.9123 // Bidar
            "w3" -> 16.1817 // Bagalkot
            "w4" -> 15.0886 // Sandur
            "w5" -> 12.6518 // Ramnagaram
            "w6" -> 12.2958 // Mysuru
            "w7" -> 17.9123 // Bidar
            "w8" -> 15.4589 // Dharwad
            "w9" -> 13.9299 // Shivamogga
            "w10" -> 15.6516 // Navalgund
            "w11" -> 17.9123 // Bidar
            "w12" -> 15.3350 // Hampi
            "w13" -> 15.3483 // Koppal
            "w14" -> 15.9189 // Ilkal
            "w15" -> 14.4101 // Dharmavaram (border area)
            "w16" -> 12.2958 // Mysuru
            "w17" -> 17.9123 // Bidar
            "w18" -> 14.1670 // Sagar
            "w19" -> 15.4589 // Dharwad
            "w20" -> 15.1394 // Bellary
            "w21" -> 15.3483 // Koppal
            "w22" -> 16.1817 // Bagalkot
            "w23" -> 17.9123 // Bidar
            "w24" -> 15.3647 // Hubli
            "w25" -> 13.0645 // Shivarapatna
            "w26" -> 12.2958 // Mysuru
            "w27" -> 17.9123 // Bidar
            "w28" -> 15.6516 // Navalgund
            "w29" -> 15.3483 // Koppal
            "w30" -> 15.0886 // Sandur
            else -> 12.9716 // Bengaluru
        },
        longitude = when(id) {
            "p1" -> 74.7421
            "p2" -> 77.1140
            "p3" -> 74.8430
            "p4" -> 75.5681
            "p5" -> 74.8560
            "p6" -> 74.4447
            "p7" -> 76.8837
            "p8" -> 74.7421
            "p9" -> 77.1852
            "p10" -> 75.7753
            "p11" -> 74.9818
            "p12" -> 75.3957
            "p13" -> 77.1140
            "p14" -> 74.1240
            "p15" -> 74.6917
            "p16" -> 76.1025
            "p17" -> 74.4231
            "p18" -> 74.7421
            "p19" -> 75.0078
            "p20" -> 76.6394
            "p21" -> 74.9664
            "p22" -> 75.4746
            "p23" -> 75.7333
            "p24" -> 75.2072
            "p25" -> 76.4000
            "p26" -> 75.3901
            "p27" -> 74.6366
            "p28" -> 75.6971
            "p29" -> 76.9416
            "p30" -> 74.7142
            "w1" -> 76.1555
            "w2" -> 77.5307
            "w3" -> 75.6971
            "w4" -> 76.5516
            "w5" -> 77.2038
            "w6" -> 76.6394
            "w7" -> 77.5307
            "w8" -> 75.0078
            "w9" -> 75.5681
            "w10" -> 75.3626
            "w11" -> 77.5307
            "w12" -> 76.4600
            "w13" -> 76.1555
            "w14" -> 75.9234
            "w15" -> 77.7126
            "w16" -> 76.6394
            "w17" -> 77.5307
            "w18" -> 75.0253
            "w19" -> 75.0078
            "w20" -> 76.9214
            "w21" -> 76.1555
            "w22" -> 75.6971
            "w23" -> 77.5307
            "w24" -> 75.1239
            "w25" -> 77.8967
            "w26" -> 76.6394
            "w27" -> 77.5307
            "w28" -> 75.3626
            "w29" -> 76.1555
            "w30" -> 76.5516
            else -> 77.5946
        },
        phone = "+919876543210"
    )

    private suspend fun refreshHeritageSites() {
        val artisans = getCollectionList("artisans", "ARTISAN", HeritageSite::class.java)
        val workshops = getCollectionList("workshops", "WORKSHOP", HeritageSite::class.java)
        
        val allSites = artisans + workshops
        if (allSites.isNotEmpty()) {
            heritageDao.refreshSites(allSites.map { it.toEntity() })
        }
    }

    private suspend fun refreshArtForms() {
        val artForms = getCollectionList("art_forms", null, ArtForm::class.java)
        if (artForms.isNotEmpty()) {
            heritageDao.refreshArtForms(artForms.map { it.toEntity() })
        }
    }

    private suspend fun refreshEvents() {
        val events = getCollectionList("events", null, ArtEvent::class.java)
        if (events.isNotEmpty()) {
            heritageDao.refreshEvents(events.map { it.toEntity() })
        }
    }

    private suspend fun refreshProducts() {
        val products = getCollectionList("products", null, Product::class.java)
        if (products.isNotEmpty()) {
            heritageDao.refreshProducts(products.map { it.toEntity() })
        }
    }

    private suspend fun <T> getCollectionList(collectionName: String, type: String?, clazz: Class<T>): List<T> {
        return try {
            val snapshot = firestore.collection(collectionName).get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    val obj = doc.toObject(clazz)
                    if (obj is HeritageSite && type != null) {
                        obj.copy(id = doc.id, type = type) as T
                    } else if (obj is ArtForm) {
                        obj.copy(id = doc.id) as T
                    } else if (obj is ArtEvent) {
                        obj.copy(id = doc.id) as T
                    } else if (obj is Product) {
                        obj.copy(id = doc.id) as T
                    } else {
                        obj
                    }
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
