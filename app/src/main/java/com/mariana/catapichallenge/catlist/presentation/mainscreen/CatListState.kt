package com.mariana.catapichallenge.catlist.presentation.mainscreen

import com.mariana.catapichallenge.catlist.module.Cat

data class CatListState(
    val isLoading: Boolean = false,
    val catListPage: Int = 1,
    val favoriteCatPage: Int = 1,

    val isCurrentListPage: Boolean = true,
    val catList: List<Cat> = emptyList(),
    val favoriteList: List<Cat> = emptyList(),
)