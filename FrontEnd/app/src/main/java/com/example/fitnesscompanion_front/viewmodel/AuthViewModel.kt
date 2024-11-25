package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val authState = MutableStateFlow<AuthState>(AuthState.Idle)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authState.value = AuthState.Loading
            try {
                val token = repository.login(email, password)
                authState.value = AuthState.Authenticated(token)
            } catch (e: Exception) {
                authState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signup(username: String, email: String, password: String) {
        viewModelScope.launch {
            authState.value = AuthState.Loading
            try {
                val token = repository.signup(username, email, password)
                authState.value = AuthState.Authenticated(token)
            } catch (e: Exception) {
                authState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val token: String) : AuthState()
    data class Error(val message: String) : AuthState()
}
