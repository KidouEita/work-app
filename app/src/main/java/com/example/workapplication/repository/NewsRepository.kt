package com.example.workapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workapplication.api.NewsService
import com.example.workapplication.api.model.NewsObj
import com.example.workapplication.utils.FetchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewsRepository {

    private val service by lazy { NewsService.create() }

    suspend fun fetchNews(): LiveData<FetchResult<NewsObj>> =
        withContext(Dispatchers.IO) {
            val result = MutableLiveData<FetchResult<NewsObj>>()

            try {
                result.postValue(FetchResult.Success(service.fetchNews()))
            } catch (e: Exception) {
                result.postValue(FetchResult.Error(e))
            }
            result
        }
}