package com.example.workapplication.api

import com.example.workapplication.api.model.NewsObj
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("news.json")
    suspend fun fetchNews(): NewsObj

    companion object {
        fun create(): NewsService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://tcgbusfs.blob.core.windows.net/dotapp/")
                .build()
            return retrofit.create(NewsService::class.java)
        }
    }

}