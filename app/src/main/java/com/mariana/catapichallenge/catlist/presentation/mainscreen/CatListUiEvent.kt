package com.mariana.catapichallenge.catlist.presentation.mainscreen

sealed interface CatListUiEvent {
    data class Paginate(val isFavorite: Boolean) : CatListUiEvent
    object Navigate: CatListUiEvent
}
