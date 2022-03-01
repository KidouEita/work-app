package com.example.workapplication.api

import com.example.workapplication.BuildConfig
import com.example.workapplication.api.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserService {

    @FormUrlEncoded
    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @POST("login")
    suspend fun login(
        @Field("username") username: String = "",
        @Field("password") password: String = ""
    ): User

    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @PUT("users/{userObjectId}")
    suspend fun editUser(
        @Header("X-Parse-Session-Token") token: String,
        @Path("userObjectId") objectId: String
    )

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