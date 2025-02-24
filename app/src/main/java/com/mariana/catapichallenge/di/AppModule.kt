package com.mariana.catapichallenge.di

import android.content.Context
import androidx.room.Room
import com.mariana.catapichallenge.BuildConfig.BASE_URL
import com.mariana.catapichallenge.catlist.data.local.CatDao
import com.mariana.catapichallenge.catlist.data.local.CatDataBase
import com.mariana.catapichallenge.catlist.data.remote.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJsonConfig() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory = json.asConverterFactory(
        contentType = "application/json; charset=UTF8".toMediaType()
    )

    @Provides
fun provideCatDao(
    dataBase: CatDataBase,
): CatDao = dataBase.catDao

    @Provides
    fun provideCatApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CatDataBase = Room.databaseBuilder(
        context,
        CatDataBase::class.java,
        "cat_api_db"
    ).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory,
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converter)
        .client(okHttpClient)
        .build()
}
