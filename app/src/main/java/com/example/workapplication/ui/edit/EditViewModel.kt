package com.example.workapplication.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workapplication.repository.RepositoryManager
import com.example.workapplication.utils.FetchResultWithNoResponse
import kotlinx.coroutines.launch

class EditViewModel : ViewModel() {

    private val repository by lazy { RepositoryManager.userRepository }

    val username get() = repository.username
    val timezone get() = repository.timezone

    private val _editResult = MutableLiveData<FetchResultWithNoResponse>()
    val editResult: LiveData<FetchResultWithNoResponse> get() = _editResult

    fun editTimezone(newTimezone:Int) {
        viewModelScope.launch {
            _editResult.postValue(repository.editTimezone(newTimezone).value)
        }
    }

}