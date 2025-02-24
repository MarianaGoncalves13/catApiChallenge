package com.mariana.catapichallenge.catlist.data.repository

import coil.network.HttpException
import com.mariana.catapichallenge.catlist.data.remote.CatApi
import com.mariana.catapichallenge.catlist.data.remote.respond.FavoriteBreed
import com.mariana.catapichallenge.catlist.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl
    @Inject constructor(
        private val catApi : CatApi
    ): FavoriteRepository {

    override suspend fun sendFavoriteBreed(favoriteId: String): Flow<FavoriteBreed> = flow {
       emit(catApi.sendFavorite(FavoriteBreed(favoriteId)))
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteFavorite(favoriteId: String?) = flow {
        checkNotNull(favoriteId) {"It can't be null"}
        catApi.deleteFavorite(favoriteId)
        emit(true)
    }.catch {
        emit(it is HttpException)
    }.flowOn(Dispatchers.IO)
}
