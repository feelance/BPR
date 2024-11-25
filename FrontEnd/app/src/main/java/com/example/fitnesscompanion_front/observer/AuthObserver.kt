package com.example.fitnesscompanion_front.observer

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.fitnesscompanion_front.viewmodel.AuthState
import com.example.fitnesscompanion_front.viewmodel.AuthViewModel

@Composable
fun AuthObserver(viewModel: AuthViewModel, onAuthenticated: () -> Unit) {
    val authState = viewModel.authState.collectAsState()

    when (val state = authState.value) {
        is AuthState.Loading -> CircularProgressIndicator()
        is AuthState.Authenticated -> onAuthenticated()
        is AuthState.Error -> {
            val message = state.message
            Text("Error: $message")
        }
        else -> {}
    }
}