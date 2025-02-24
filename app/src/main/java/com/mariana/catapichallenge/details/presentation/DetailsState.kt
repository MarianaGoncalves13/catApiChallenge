package com.mariana.catapichallenge.details.presentation

import com.mariana.catapichallenge.catlist.domain.module.Cat

data class DetailsState(
    val isLoading: Boolean = false,
    val breed: Cat? = null
)