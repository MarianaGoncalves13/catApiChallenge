package com.mariana.catapichallenge.catlist.data.remote

import com.mariana.catapichallenge.catlist.data.remote.respond.BreedDto
import com.mariana.catapichallenge.catlist.data.remote.respond.FavoriteBreed
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {
    @GET("/v1/breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): List<BreedDto>

    @GET("v1/favourites")
    suspend fun getFavorites(): List<FavoriteBreed>

    @DELETE("v1/favourites/{id}")
    suspend fun deleteFavorite(
        @Path("id") id: String
    )

    @POST("v1/favourites")
    suspend fun sendFavorite(
        @Body payload: FavoriteBreed
    ): FavoriteBreed
    companion object {
        const val BASE_URL_IMAGE = "https://api.thecatapi.com/v1/images/"
    }
}
