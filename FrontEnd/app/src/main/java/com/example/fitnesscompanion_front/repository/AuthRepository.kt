package com.example.fitnesscompanion_front.repository

import retrofit2.http.Body
import retrofit2.http.POST

class AuthRepository(private val api: AuthApi) {
    suspend fun login(email: String, password: String): String {
        val response = api.login(LoginRequest(email, password))
        return response.token
    }

    suspend fun signup(username: String, email: String, password: String): String {
        val response = api.signup(SignupRequest(username, email, password))
        return response.token
    }
}

data class LoginRequest(val email: String, val password: String)
data class SignupRequest(val username: String, val email: String, val password: String)

data class AuthResponse(val token: String)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/signup")
    suspend fun signup(@Body request: SignupRequest): AuthResponse
}