package com.phdteam.historyverse.ui.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val authRepository: AuthRepository) : ViewModel() {
    val _isSignedIn = MutableStateFlow(false)
    val isSignedIn = _isSignedIn.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.checkSignInState().let {
                _isSignedIn.update {
                    it
                }
            }
        }
    }
}