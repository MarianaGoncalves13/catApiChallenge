package com.mariana.catapichallenge.catlist.data.mappers

import com.mariana.catapichallenge.catlist.data.local.CatEntity
import com.mariana.catapichallenge.catlist.data.remote.respond.BreedDto
import com.mariana.catapichallenge.catlist.domain.module.Cat

fun BreedDto.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        description = description,
        name = name,
        origin = origin,
        temperament = temperament,
        imageUrl = reference_image_id
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
