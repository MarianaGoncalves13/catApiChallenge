package com.mariana.catapichallenge.catlist.data.remote

import com.mariana.catapichallenge.catlist.data.remote.respond.BreedDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("/v1/breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): List<BreedDto>

    companion object {
        const val BASE_URL_IMAGE = "https://api.thecatapi.com/v1/images/"
    }
}
