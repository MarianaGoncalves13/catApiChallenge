package com.mariana.catapichallenge.catlist.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CatDao {

    @Upsert
    suspend fun upsertCatList(catList:List<CatEntity>)

    @Query("SELECT * FROM CatEntity")
    fun getAllBreeds(): List<CatEntity>

    @Query("SELECT * FROM CatEntity WHERE id = :id")
    suspend fun getCatById(id: String): CatEntity
}
