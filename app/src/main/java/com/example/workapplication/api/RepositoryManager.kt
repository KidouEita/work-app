package com.example.workapplication.api

object RepositoryManager {

    val userRepository by lazy { UserRepository() }
    val newsRepository by lazy { NewsRepository() }
}