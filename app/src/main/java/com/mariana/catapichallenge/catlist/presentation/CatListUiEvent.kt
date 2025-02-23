package com.mariana.catapichallenge.catlist.presentation

sealed interface CatListUiEvent {
    data class Paginate(val isFavorite: Boolean) : CatListUiEvent
    object Navigate: CatListUiEvent
}
