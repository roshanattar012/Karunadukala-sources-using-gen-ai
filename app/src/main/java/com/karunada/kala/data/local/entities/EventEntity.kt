package com.karunada.kala.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karunada.kala.data.ArtEvent

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val videoUrl: String
)

fun EventEntity.toDomain() = ArtEvent(
    id = id,
    title = title,
    description = description,
    date = date,
    location = location,
    videoUrl = videoUrl
)

fun ArtEvent.toEntity() = EventEntity(
    id = id,
    title = title,
    description = description,
    date = date,
    location = location,
    videoUrl = videoUrl
)
