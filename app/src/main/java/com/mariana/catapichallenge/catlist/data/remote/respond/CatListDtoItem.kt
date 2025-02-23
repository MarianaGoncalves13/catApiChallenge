package com.mariana.catapichallenge.catlist.data.remote.respond

data class CatListDtoItem(
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
    val isFavorite: Boolean
)
