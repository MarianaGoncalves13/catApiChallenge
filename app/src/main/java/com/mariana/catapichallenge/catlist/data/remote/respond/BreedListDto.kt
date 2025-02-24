package com.mariana.catapichallenge.catlist.data.remote.respond

import kotlinx.serialization.Serializable

@Serializable
data class BreedListDto(
    val breeds: List<BreedDto> = listOf(),
    val height: Int = 0,
    val id: String = "",
    val url: String = "",
    val width: Int = 0
)
