package com.karunada.kala.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.karunada.kala.data.*
import com.karunada.kala.data.local.HeritageDao
import com.karunada.kala.data.local.entities.*
import com.karunada.kala.data.local.toDomain
import com.karunada.kala.data.local.toEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeritageRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
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

    suspend fun toggleSave(id: String, type: String, isSaved: Boolean) {
        if (isSaved) {
            heritageDao.unsaveItem(id)
        } else {
            heritageDao.saveItem(SavedEntity(id, type))
        }
    }

    fun isItemSaved(id: String): Flow<Boolean> = heritageDao.isItemSaved(id)

    suspend fun refreshAllData() {
        try {
            refreshHeritageSites()
            refreshArtForms()
            refreshEvents()
            refreshProducts()
            
            // Fallback: If local DB is still empty, populate with MockData
            checkAndPopulateMockData()
        } catch (e: Exception) {
            checkAndPopulateMockData()
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
            "a1" -> 13.3409 // Udupi
            "a2" -> 15.3483 // Koppal
            "a3" -> 17.9123 // Bidar
            "a4" -> 15.9555 // Bagalkot
            "a5" -> 13.3392 // Tumkur
            "a6" -> 12.2958 // Mysuru
            "a7" -> 15.1394 // Bellary
            "a8" -> 15.4589 // Dharwad
            else -> 12.9716 // Bengaluru
        },
        longitude = when(id) {
            "a1" -> 74.7421
            "a2" -> 76.1555
            "a3" -> 77.5307
            "a4" -> 75.1133
            "a5" -> 77.1140
            "a6" -> 76.6394
            "a7" -> 76.9214
            "a8" -> 75.0078
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
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
