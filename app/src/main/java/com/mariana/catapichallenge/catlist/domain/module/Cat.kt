package com.mariana.catapichallenge.catlist.domain.module

data class Cat(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val imageUrl: String?,
    val lifeSpan: String
)
