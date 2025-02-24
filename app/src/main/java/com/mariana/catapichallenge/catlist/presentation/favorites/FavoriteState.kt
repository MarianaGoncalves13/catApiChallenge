package com.mariana.catapichallenge.catlist.presentation.favorites

import androidx.compose.runtime.Immutable
import com.mariana.catapichallenge.catlist.module.Cat

@Immutable
data class FavoriteState(
    val isLoading: Boolean = true,
    val favoriteList: List<Cat> = emptyList()
)