package com.mariana.catapichallenge.catlist.presentation.details

import com.mariana.catapichallenge.catlist.module.Cat

data class DetailsState(
    val isLoading: Boolean = false,
    val breed: Cat? = null
)