package com.example.workapplication.repository

object RepositoryManager {

    val userRepository by lazy { UserRepository() }
    val newsRepository by lazy { NewsRepository() }
}