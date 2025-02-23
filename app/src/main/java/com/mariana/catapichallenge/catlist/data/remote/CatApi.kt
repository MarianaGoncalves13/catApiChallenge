package com.mariana.catapichallenge.catlist.data.remote

import com.mariana.catapichallenge.catlist.data.remote.respond.CatListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("v1/breeds")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): CatListDto


    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val BASE_URL_IMAGE = "https://api.thecatapi.com/v1/images/"
        const val API_KEY = "live_JN3nEon0zBg3jqhqRtZEX9Z3dzYgvwkLinUELdlwwmd9eQjV1urr0jIIqdLKZJHZ"
    }
}
