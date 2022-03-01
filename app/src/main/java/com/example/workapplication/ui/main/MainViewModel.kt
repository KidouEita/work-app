package com.example.workapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workapplication.utils.FetchResult
import com.example.workapplication.repository.RepositoryManager
import com.example.workapplication.api.model.User
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository by lazy { RepositoryManager.userRepository }

    private val _userInfo = MutableLiveData<FetchResult<User>>()
    val userInfo: LiveData<FetchResult<User>> get() = _userInfo

    fun tryLogin(username: String, password: String) {
        viewModelScope.launch {
            _userInfo.postValue(repository.login(username, password).value)
        }
    }

    // TODO store username/password into pref ?
}