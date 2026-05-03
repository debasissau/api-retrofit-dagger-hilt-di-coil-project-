package com.example.apiretrofitdaggerhiltdicoilproject.data.remoteapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {
    private val BASE_URL = "https://fakestoreapi.com"

    @get:Provides
    @Singleton
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

/*
Dagger Hilt managing everything behind the scenes as a constructor:
   -productUI ---> ProductViewModel --> ProductRepository ---> ApiService --->  ApiService is created inside RetrofitInstance

 */