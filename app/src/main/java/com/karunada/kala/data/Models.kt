package com.karunada.kala.data

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

enum class HeritageType {
    WORKSHOP, PERFORMANCE, ARTISAN
}

@IgnoreExtraProperties
data class HeritageSite(
    @get:Exclude
    val id: String = "",
    val name: String = "",
    val type: String = "", // "WORKSHOP", "EVENT", "ARTISAN"
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val imageUrl: String = "",
    val description: String = "",
    val phone: String? = null,
    val artForm: String = "",
    val district: String = ""
) {
    val location: LatLng get() = LatLng(latitude, longitude)
    
    val heritageType: HeritageType get() = when(type.uppercase()) {
        "WORKSHOP" -> HeritageType.WORKSHOP
        "EVENT", "PERFORMANCE" -> HeritageType.PERFORMANCE
        else -> HeritageType.ARTISAN
    }
}

@IgnoreExtraProperties
data class ArtForm(
    @get:Exclude
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val shortDesc: String = "",
    val history: String = "",
    val origin: String = "",
    val imageUrl: String = "",
    val videoUrl: String = ""
)

@IgnoreExtraProperties
data class Artisan(
    @get:Exclude
    val id: String = "",
    val name: String = "",
    val artForm: String = "",
    val district: String = "",
    val imageUrl: String = "",
    val type: HeritageType = HeritageType.ARTISAN
)

@IgnoreExtraProperties
data class ArtEvent(
    @get:Exclude
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val location: String = "",
    val videoUrl: String = ""
)

@IgnoreExtraProperties
data class Product(
    @get:Exclude
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val artisanId: String = "",
    val description: String = "",
    val category: String = ""
)
