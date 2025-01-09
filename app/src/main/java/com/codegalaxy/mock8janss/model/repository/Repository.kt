package com.codegalaxy.mock8janss.model.repository

import NoInternetException
import com.codegalaxy.mock8janss.model.networkconnectivity.NetworkConnectivity
import com.codegalaxy.mock8janss.model.dto.User
import com.codegalaxy.mock8janss.model.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val networkConnectivity: NetworkConnectivity
):IRepository {

    override suspend fun getUsers(): Flow<Result<List<User>>> = flow {

        if (networkConnectivity.isNetworkAvailable()) {
            try {
                val response = apiService.getUsers()
                emit(Result.success(response))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        } else {
            emit(Result.failure(NoInternetException()))
        }

    }.flowOn(Dispatchers.IO)

}