package com.karunada.kala.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karunada.kala.data.HeritageSite

@Entity(tableName = "heritage_sites")
data class HeritageEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String,
    val description: String,
    val phone: String?,
    val artForm: String,
    val district: String
)

fun HeritageEntity.toDomain() = HeritageSite(
    id = id,
    name = name,
    type = type,
    latitude = latitude,
    longitude = longitude,
    imageUrl = imageUrl,
    description = description,
    phone = phone,
    artForm = artForm,
    district = district
)

fun HeritageSite.toEntity() = HeritageEntity(
    id = id,
    name = name,
    type = type,
    latitude = latitude,
    longitude = longitude,
    imageUrl = imageUrl,
    description = description,
    phone = phone,
    artForm = artForm,
    district = district
)
