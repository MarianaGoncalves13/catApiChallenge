package com.mariana.catapichallenge.catlist.data.remote

import com.mariana.catapichallenge.catlist.data.remote.respond.BreedListDto
import retrofit2.http.GET

interface CatApi {

    @GET("/v1/breeds")
    suspend fun getBreeds(): BreedListDto

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val BASE_URL_IMAGE = "https://api.thecatapi.com/v1/images/"
    }
}
