package com.example.fitnesscompanion_front.retrofit
import retrofit2.http.GET

import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.response.GenericResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface WeekRoutineApi {
    @GET("/weekRoutines/user/{id}")
    suspend fun getWeeklyRoutines(@Path("id") userId: Int): List<WeekRoutine>

    @POST("/weekRoutines") // Replace with your actual API endpoint
    suspend fun saveWeekRoutine(@Body routine: WeekRoutineRequest): GenericResponse
}