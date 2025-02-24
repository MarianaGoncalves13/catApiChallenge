package com.mariana.catapichallenge.catlist.domain.repository

import com.mariana.catapichallenge.catlist.data.remote.respond.FavoriteBreed
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun sendFavoriteBreed(favoriteId: String) : Flow<FavoriteBreed>

    suspend fun deleteFavorite(favoriteId: String?): Flow<Boolean>
}
