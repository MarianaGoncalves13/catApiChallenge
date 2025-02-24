package com.mariana.catapichallenge.catlist.data.remote.respond

import kotlinx.serialization.Serializable

@Serializable
data class BreedDto(
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    val temperament: String = "",
    val description: String = "",
    val reference_image_id: String = "",
    val weight: Weight = Weight(),
)
