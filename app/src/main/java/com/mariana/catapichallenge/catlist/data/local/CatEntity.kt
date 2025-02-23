package com.mariana.catapichallenge.catlist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey
    val id: String,
    val adaptability: Int,
    val affectionLevel: Int,
    val description: String,
    val energyLevel: Int,
    val indoor: Int,
    val intelligence: Int,
    val name: String,
    val natural: Int,
    val origin: String,
    val temperament: String,
)