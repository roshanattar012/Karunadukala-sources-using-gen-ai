package com.karunada.kala.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_items")
data class SavedEntity(
    @PrimaryKey val id: String,
    val type: String, // "ART_FORM", "ARTISAN", "PRODUCT"
    val timestamp: Long = System.currentTimeMillis()
)
