package com.mariana.catapichallenge.catlist.data.remote.respond

data class CatDto(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val imageUrl: String?,
)
