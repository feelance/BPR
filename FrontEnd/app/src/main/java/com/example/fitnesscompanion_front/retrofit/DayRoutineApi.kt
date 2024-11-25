package com.example.fitnesscompanion_front.retrofit
import com.example.fitnesscompanion_front.model.DayRoutine
import retrofit2.http.GET

import retrofit2.http.Path

interface DayRoutineApi {
    @GET("/dayRoutines/byWeekRoutine/{id}")
    suspend fun getDayRoutinesByWeekRoutineId(@Path("id") weekRoutineId: Long): List<DayRoutine>
}