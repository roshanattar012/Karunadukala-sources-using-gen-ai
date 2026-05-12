package com.karunada.kala.data.local

import androidx.room.*
import com.karunada.kala.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HeritageDao {
    // Heritage Sites
    @Query("SELECT * FROM heritage_sites")
    fun getAllSites(): Flow<List<HeritageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSites(sites: List<HeritageEntity>)

    @Query("DELETE FROM heritage_sites")
    suspend fun deleteAllSites()

    @Transaction
    suspend fun refreshSites(sites: List<HeritageEntity>) {
        deleteAllSites()
        insertSites(sites)
    }

    // Art Forms
    @Query("SELECT * FROM art_forms")
    fun getAllArtForms(): Flow<List<ArtFormEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtForms(artForms: List<ArtFormEntity>)

    @Query("DELETE FROM art_forms")
    suspend fun deleteAllArtForms()

    @Transaction
    suspend fun refreshArtForms(artForms: List<ArtFormEntity>) {
        deleteAllArtForms()
        insertArtForms(artForms)
    }

    // Events
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Query("DELETE FROM events")
    suspend fun deleteAllEvents()

    @Transaction
    suspend fun refreshEvents(events: List<EventEntity>) {
        deleteAllEvents()
        insertEvents(events)
    }

    // Products
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Transaction
    suspend fun refreshProducts(products: List<ProductEntity>) {
        deleteAllProducts()
        insertProducts(products)
    }

    // Favorites / Saved Items
    @Query("SELECT * FROM saved_items ORDER BY timestamp DESC")
    fun getSavedItems(): Flow<List<SavedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: SavedEntity)

    @Query("DELETE FROM saved_items WHERE id = :id")
    suspend fun unsaveItem(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_items WHERE id = :id)")
    fun isItemSaved(id: String): Flow<Boolean>
}
