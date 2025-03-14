package com.mariana.catapichallenge.catlist.data.repository

import coil.network.HttpException
import com.mariana.catapichallenge.catlist.data.local.CatDataBase
import com.mariana.catapichallenge.catlist.data.mappers.toCat
import com.mariana.catapichallenge.catlist.data.mappers.toCatEntity
import com.mariana.catapichallenge.catlist.data.remote.CatApi
import com.mariana.catapichallenge.catlist.module.Cat
import com.mariana.catapichallenge.catlist.domain.repository.CatListRepository
import com.mariana.catapichallenge.catlist.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import javax.inject.Inject

class CatListRepositoryImpl @Inject constructor(
    private val catApi: CatApi,
    private val catDataBase: CatDataBase
) : CatListRepository {

    //This will be used for the list of cats
    override suspend fun getCatListWithBreed(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Cat>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCatList = catDataBase.catDao.getAllBreeds()
            val shouldLocalCatList = localCatList.isNotEmpty() && !forceFetchFromRemote
            if (shouldLocalCatList) {
                emit(Resource.Success(
                    data = localCatList.map { catEntity ->
                        catEntity.toCat()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val catListFromApi = try {
                catApi.getBreeds(10, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading Cats"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading Cats"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading Cats"))
                return@flow
            }

            val catEntities = catListFromApi.let {
                it.map { catDto ->
                    catDto.toCatEntity()
                }
            }
            catDataBase.catDao.upsertCatList(catEntities)
            emit(Resource.Success(
                catEntities.map {
                    it.toCat()
                }
            ))
            emit(Resource.Loading(false))
        }.flowOn(Dispatchers.IO)
    }

    //This will be used for the details
    override suspend fun getCat(id: String): Flow<Resource<Cat>> {
        return flow {
            emit(Resource.Loading(true))

            val catEntity = catDataBase.catDao.getCatById(id)
            emit(Resource.Success(catEntity.toCat()))
            emit(Resource.Loading(false))
            return@flow
        }
    }
}
