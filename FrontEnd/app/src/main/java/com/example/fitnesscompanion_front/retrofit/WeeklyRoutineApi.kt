package com.example.fitnesscompanion_front.retrofit
import retrofit2.http.GET

import com.example.fitnesscompanion_front.model.WeeklyRoutine
import retrofit2.http.Query

interface WeeklyRoutineApi {
    @GET("/weekRoutines/user/{id}")
    suspend fun getWeeklyRoutines(@Query("id") userId: Long): List<WeeklyRoutine>
}