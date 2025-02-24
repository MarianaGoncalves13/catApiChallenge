package com.mariana.catapichallenge.di

import com.mariana.catapichallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("x-api-key", BuildConfig.API_KEY)

        return chain.proceed(requestBuilder.build())
    }
}