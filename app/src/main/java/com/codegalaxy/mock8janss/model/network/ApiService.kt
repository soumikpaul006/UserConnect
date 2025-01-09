package com.codegalaxy.mock8janss.model.network

import com.codegalaxy.mock8janss.model.dto.User
import retrofit2.http.GET

interface ApiService {

    @GET("test/user")
    suspend fun getUsers():List<User>
}