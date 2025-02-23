package com.mariana.catapichallenge.di

import android.content.Context
import androidx.room.Room
import com.mariana.catapichallenge.BuildConfig
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
fun provideCatDao(
    dataBase: CatDataBase,
): CatDao = dataBase.catDao

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory = json.asConverterFactory(
        contentType = "application/json; charset=UTF8".toMediaType()
    )


    @Provides
    fun provideCatApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(CatApi.BASE_URL)
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    fun provideCatDatabase(
        @ApplicationContext context: Context
    ): CatDataBase {
        return Room.databaseBuilder(
            context,
            CatDataBase::class.java,
            "catdb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addNetworkInterceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("x-api-key", BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory,
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(converter)
        .client(okHttpClient)
        .build()
}
