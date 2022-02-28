package com.example.workapplication.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workapplication.api.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {

    private val service by lazy { UserService.create() }

    suspend fun login(username: String, password: String): LiveData<FetchResult<User>> =
        withContext(Dispatchers.IO) {
            val result = MutableLiveData<FetchResult<User>>()

            try {
                val user = service.login(username, password)
                result.postValue(FetchResult.Success(user))
            } catch (e: Exception) {
                result.postValue(FetchResult.Error(e))
            }

            result
        }

    suspend fun editUser(): LiveData<FetchResultWithNoResponse> =
        withContext(Dispatchers.IO) {
            val result = MutableLiveData<FetchResultWithNoResponse>()

            try {
                result.postValue(FetchResultWithNoResponse.Success)
            } catch (e: Exception) {
                result.postValue(FetchResultWithNoResponse.Error(e))
            }

            result
        }

}