package com.mariana.catapichallenge.catlist.data.remote.respond

data class CatListDto(
    val results: List<CatDto>,
    val id: String,
    val url: String
)
