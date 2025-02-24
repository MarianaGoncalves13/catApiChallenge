package com.mariana.catapichallenge.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariana.catapichallenge.catlist.domain.repository.CatListRepository
import com.mariana.catapichallenge.catlist.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val catListRepository: CatListRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val breedId = savedStateHandle.get<String>("catId")
    private var _detailState = MutableStateFlow(DetailsState())
    val detailsState = _detailState.asStateFlow()

    init {
        getCat(breedId ?: "catId")
    }

    private fun getCat(id: String) {
        viewModelScope.launch {
            _detailState.update {
                it.copy(isLoading = true)
            }
            catListRepository.getCat(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _detailState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { breed ->
                            _detailState.update {
                                it.copy(breed = breed)
                            }
                        }
                    }
                }
            }
        }
    }
}
