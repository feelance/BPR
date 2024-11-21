package com.example.fitnesscompanion_front.retrofit
import retrofit2.http.GET

import com.example.fitnesscompanion_front.model.WeeklyRoutine

interface WeeklyRoutineApi {
    @GET("weekRoutines")
    suspend fun getWeeklyRoutines(): List<WeeklyRoutine>
}