package com.mariana.catapichallenge.catlist.data.local.mappers

import com.mariana.catapichallenge.catlist.data.local.CatEntity
import com.mariana.catapichallenge.catlist.data.remote.respond.CatListDtoItem
import com.mariana.catapichallenge.catlist.domain.module.Cat

fun CatListDtoItem.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        adaptability = adaptability,
        affectionLevel = affectionLevel,
        description = description,
        energyLevel = energyLevel,
        indoor = indoor,
        intelligence = intelligence,
        name = name,
        natural = natural,
        origin = origin,
        temperament = temperament
    )
}

fun CatEntity.toCat(): Cat {
    return Cat(
        id = id,
        adaptability = adaptability,
        affectionLevel = affectionLevel,
        description = description,
        energyLevel = energyLevel,
        indoor = indoor,
        intelligence = intelligence,
        name = name,
        natural = natural,
        origin = origin,
        temperament = temperament
    )
}
