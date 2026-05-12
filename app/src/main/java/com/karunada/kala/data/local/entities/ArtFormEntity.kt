package com.karunada.kala.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karunada.kala.data.ArtForm

@Entity(tableName = "art_forms")
data class ArtFormEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val shortDesc: String,
    val history: String,
    val origin: String,
    val imageUrl: String,
    val videoUrl: String
)

fun ArtFormEntity.toDomain() = ArtForm(
    id = id,
    name = name,
    category = category,
    shortDesc = shortDesc,
    history = history,
    origin = origin,
    imageUrl = imageUrl,
    videoUrl = videoUrl
)

fun ArtForm.toEntity() = ArtFormEntity(
    id = id,
    name = name,
    category = category,
    shortDesc = shortDesc,
    history = history,
    origin = origin,
    imageUrl = imageUrl,
    videoUrl = videoUrl
)
