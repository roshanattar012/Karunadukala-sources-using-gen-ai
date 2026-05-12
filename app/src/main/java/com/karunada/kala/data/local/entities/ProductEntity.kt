package com.karunada.kala.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karunada.kala.data.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val artisanId: String,
    val description: String,
    val category: String
)

fun ProductEntity.toDomain() = Product(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    artisanId = artisanId,
    description = description,
    category = category
)

fun Product.toEntity() = ProductEntity(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    artisanId = artisanId,
    description = description,
    category = category
)
