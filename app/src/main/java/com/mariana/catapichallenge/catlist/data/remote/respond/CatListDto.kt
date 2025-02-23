package com.mariana.catapichallenge.catlist.data.remote.respond

data class CatListDto(
    val page: Int,
    val results: List<CatListDtoItem>
)