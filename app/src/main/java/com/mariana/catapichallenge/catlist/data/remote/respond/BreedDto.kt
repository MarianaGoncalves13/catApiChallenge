package com.mariana.catapichallenge.catlist.data.remote.respond

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
)
