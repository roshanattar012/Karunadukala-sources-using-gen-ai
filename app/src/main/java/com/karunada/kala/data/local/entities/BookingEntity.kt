package com.karunada.kala.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.UUID

@IgnoreExtraProperties
@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey
    @get:Exclude
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val artForm: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
