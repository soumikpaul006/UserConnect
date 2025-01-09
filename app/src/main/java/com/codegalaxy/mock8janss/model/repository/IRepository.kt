package com.codegalaxy.mock8janss.model.repository

import com.codegalaxy.mock8janss.model.dto.User
import kotlinx.coroutines.flow.Flow

interface IRepository {

    suspend fun getUsers(): Flow<Result<List<User>>>
}