package com.mariana.catapichallenge.catlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariana.catapichallenge.catlist.domain.repository.CatListRepository
import com.mariana.catapichallenge.catlist.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val catListRepository: CatListRepository
) : ViewModel() {

    private var _catListState = MutableStateFlow(CatListState())
    val catListState = _catListState.asStateFlow()

    init {
        getCatList(false)
        getFavoriteList(false)
    }

    fun onEvent(event: CatListUiEvent) {
        when (event) {
            CatListUiEvent.Navigate -> {
                _catListState.update {
                    it.copy(
                        isCurrentListPage = !catListState.value.isCurrentListPage
                    )
                }
            }

            is CatListUiEvent.Paginate -> {
                if (event.isFavorite) {
                    getFavoriteList(true)
                } else {
                    getCatList(true)
                }
            }
        }
    }

    private fun getCatList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _catListState.update {
                it.copy(isLoading = true)
            }
            catListRepository.getCatListWithBreed(
                forceFetchFromRemote,
                catListState.value.catListPage
            ).collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _catListState.update {
                            it.copy(isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _catListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _catListState.update {
                                it.copy(
                                    catList = catListState.value.catList,
                                    catListPage = catListState.value.catListPage + 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getFavoriteList(forceFetchFromRemote: Boolean) {
        //implement to get the favorite cats
    }
}
