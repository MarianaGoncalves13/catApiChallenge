package com.mariana.catapichallenge.catlist.data.mappers

import com.mariana.catapichallenge.catlist.data.local.CatEntity
import com.mariana.catapichallenge.catlist.data.remote.respond.CatDto
import com.mariana.catapichallenge.catlist.domain.module.Cat

fun CatDto.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        description = description,
        name = name,
        origin = origin,
        temperament = temperament,
        imageUrl = imageUrl
    )
}

fun CatEntity.toCat(): Cat {
    return Cat(
        id = id,
        description = description,
        name = name,
        origin = origin,
        temperament = temperament,
        imageUrl = imageUrl
    )
}
