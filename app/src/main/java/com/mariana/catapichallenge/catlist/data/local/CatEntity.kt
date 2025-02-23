package com.mariana.catapichallenge.catlist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey
    val id: String,

    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val imageUrl: String?,
)
