package com.example.workapplication.ui.edit

import androidx.lifecycle.ViewModel
import com.example.workapplication.repository.RepositoryManager

class EditViewModel : ViewModel() {

    private val repository by lazy { RepositoryManager.userRepository }

    val username get() = repository.username



}