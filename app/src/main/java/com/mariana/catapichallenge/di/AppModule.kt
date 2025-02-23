package com.mariana.catapichallenge.di

import android.content.Context
import androidx.room.Room
import com.mariana.catapichallenge.catlist.data.local.CatDao
import com.mariana.catapichallenge.catlist.data.local.CatDataBase
import com.mariana.catapichallenge.catlist.data.remote.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

@Provides
fun provideCatDao(
    dataBase: CatDataBase,
): CatDao = dataBase.catDao

    @Provides
    fun provideCatApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(CatApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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
}
