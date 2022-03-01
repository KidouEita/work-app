package com.example.workapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workapplication.utils.FetchResult
import com.example.workapplication.repository.RepositoryManager
import com.example.workapplication.api.model.NewsObj
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val repository by lazy { RepositoryManager.newsRepository }

    private val _news = MutableLiveData<FetchResult<NewsObj>>()

    val news: LiveData<FetchResult<NewsObj>> get() = _news

    fun fetchNews() {
        viewModelScope.launch {
            _news.postValue(repository.fetchNews().value)
        }
    }

}