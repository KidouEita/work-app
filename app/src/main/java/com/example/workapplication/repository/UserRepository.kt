package com.example.workapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workapplication.utils.PreferenceManager
import com.example.workapplication.api.UserService
import com.example.workapplication.api.model.Timezone
import com.example.workapplication.api.model.User
import com.example.workapplication.utils.FetchResult
import com.example.workapplication.utils.FetchResultWithNoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {

    private val service by lazy { UserService.create() }
    val username get() = PreferenceManager.username
    val timezone get() = PreferenceManager.timezone

    suspend fun login(username: String, password: String): LiveData<FetchResult<User>> =
        withContext(Dispatchers.IO) {
            val result = MutableLiveData<FetchResult<User>>()

            try {
                val user = service.login(username, password)
                result.postValue(FetchResult.Success(user))
                saveUserInfo(user.username, user.objectId, user.sessionToken, user.timezone ?: 8)
            } catch (e: Exception) {
                result.postValue(FetchResult.Error(e))
            }

            result
        }

    suspend fun editTimezone(newTimezone: Int): LiveData<FetchResultWithNoResponse> =
        withContext(Dispatchers.IO) {
            val result = MutableLiveData<FetchResultWithNoResponse>()

            try {
                service.editTimezone(
                    PreferenceManager.token ?: "",
                    PreferenceManager.objectId ?: "",
                    Timezone(newTimezone)
                )
                result.postValue(FetchResultWithNoResponse.Success)
                saveUserInfo(timezone = newTimezone)
            } catch (e: Exception) {
                result.postValue(FetchResultWithNoResponse.Error(e))
            }

            result
        }

    private fun saveUserInfo(
        username: String? = PreferenceManager.username,
        objectId: String? = PreferenceManager.objectId,
        token: String? = PreferenceManager.token,
        timezone: Int = PreferenceManager.timezone
    ) {
        PreferenceManager.let {
            PreferenceManager.username = username
            PreferenceManager.objectId = objectId
            PreferenceManager.token = token
            PreferenceManager.timezone = timezone
        }
    }

}