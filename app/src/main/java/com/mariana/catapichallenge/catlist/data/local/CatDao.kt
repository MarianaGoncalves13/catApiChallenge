package com.mariana.catapichallenge.catlist.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CatDao {

    @Upsert
    suspend fun upsertCatList(catList:List<CatEntity>)

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): List<CatEntity>

    @Query("SELECT * FROM breeds WHERE id = :id")
    suspend fun getCatById(id: String): CatEntity

    @Query("UPDATE breeds set favorite_id = :favoriteId where id = :id")
    suspend fun updateFavorite(id: String, favoriteId: Long)

    @Query("UPDATE breeds set favorite_id = null")
    suspend fun removeAllFavorite()
}
