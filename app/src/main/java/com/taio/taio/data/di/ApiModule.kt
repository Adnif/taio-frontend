package com.taio.taio.data.di

import com.taio.taio.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module

object ApiModule {
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://localhost/:800")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }


}