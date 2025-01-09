package com.codegalaxy.mock8janss.model.di

import android.content.Context
import com.codegalaxy.mock8janss.model.networkconnectivity.NetworkConnectivity
import com.codegalaxy.mock8janss.model.network.ApiService
import com.codegalaxy.mock8janss.model.repository.IRepository
import com.codegalaxy.mock8janss.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://6750a34e69dc1669ec1bd9c2.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkConnectivity(@ApplicationContext context: Context) : NetworkConnectivity {
        return NetworkConnectivity(context)
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        networkConnectivity: NetworkConnectivity
    ):IRepository{
        return Repository(apiService,networkConnectivity)
    }

}