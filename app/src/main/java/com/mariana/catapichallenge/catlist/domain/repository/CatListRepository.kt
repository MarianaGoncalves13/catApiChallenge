package com.mariana.catapichallenge.catlist.domain.repository

import com.mariana.catapichallenge.catlist.domain.module.Cat
import com.mariana.catapichallenge.catlist.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CatListRepository {
    suspend fun getCatListWithBreed(forceFetchFromRemote: Boolean, page: Int): Flow<Resource<List<Cat>>>
    suspend fun getCat(id: String): Flow<Resource<Cat>>
}
