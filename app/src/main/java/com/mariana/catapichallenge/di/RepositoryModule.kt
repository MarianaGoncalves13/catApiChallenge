package com.mariana.catapichallenge.di

import com.mariana.catapichallenge.catlist.data.repository.CatListRepositoryImpl
import com.mariana.catapichallenge.catlist.domain.repository.CatListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatListRepository(
        catListRepositoryImpl: CatListRepositoryImpl
    ): CatListRepository
}
