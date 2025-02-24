package com.mariana.catapichallenge.catlist.presentation.favorites

import androidx.lifecycle.ViewModel
import com.mariana.catapichallenge.catlist.domain.repository.CatListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val catListRepository: CatListRepository
): ViewModel() {
    //TODO implement the viewmodel for the favorites screen
}