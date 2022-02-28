package com.example.workapplication.api

import com.example.workapplication.BuildConfig
import com.example.workapplication.api.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @POST("login")
    suspend fun login(): User

    @PUT("users/{userObjectId}")
    suspend fun editUser(@Path("userObjectId") objectId: String)

    companion object {
        fun create(): UserService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_SERVER)
                .build()
            return retrofit.create(UserService::class.java)
        }
    }

}