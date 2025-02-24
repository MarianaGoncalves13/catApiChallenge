package com.mariana.catapichallenge.catlist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class CatEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    @ColumnInfo("reference_image_id")
    val imageUrl: String?,
)
