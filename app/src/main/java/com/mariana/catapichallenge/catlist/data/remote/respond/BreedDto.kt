package com.mariana.catapichallenge.catlist.data.remote.respond

import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedDto(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    @SerialName("reference_image_id")
    val imageUrl: String? = null,
    @SerialName("life_span")
    val lifeSpan: String,
    @ColumnInfo("favorite_id")
    val favoriteId: String,
)

@Serializable
data class FavoriteBreed(
    val id: String,
    @SerialName("image_id")
    val breedId: String? = null,
    @SerialName("sub_id")
    val userId: String? = null,
)
