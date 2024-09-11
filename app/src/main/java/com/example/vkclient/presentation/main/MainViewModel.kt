package com.example.vkclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclient.data.repository.FeedPostRepositoryImpl
import com.example.vkclient.domain.usecases.CheckAuthStateUseCase
import com.example.vkclient.domain.usecases.GetAuthStateUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FeedPostRepositoryImpl(application)

    private val getAuthStateUseCase = GetAuthStateUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository)

    val authState = getAuthStateUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }
}