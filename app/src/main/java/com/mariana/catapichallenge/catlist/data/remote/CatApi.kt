package com.mariana.catapichallenge.catlist.data.remote

import com.mariana.catapichallenge.catlist.data.local.CatEntity
import com.mariana.catapichallenge.catlist.data.remote.respond.CatDto
import com.mariana.catapichallenge.catlist.data.remote.respond.CatListDto
import retrofit2.http.GET

interface CatApi {

    @GET("https://api.thecatapi.com/v1/breeds")
    suspend fun getBreeds(): CatListDto


    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val BASE_URL_IMAGE = "https://api.thecatapi.com/v1/images/"
    }
}
