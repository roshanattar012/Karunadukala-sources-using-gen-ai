package com.karunada.kala.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.karunada.kala.data.local.entities.*

@Database(
    entities = [
        HeritageEntity::class,
        ArtFormEntity::class,
        EventEntity::class,
        ProductEntity::class,
        SavedEntity::class,
        BookingEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heritageDao(): HeritageDao
}
