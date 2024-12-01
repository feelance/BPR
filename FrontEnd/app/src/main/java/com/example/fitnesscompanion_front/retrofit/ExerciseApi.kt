package com.example.fitnesscompanion_front.retrofit
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.model.response.DayRoutineResponse
import com.example.fitnesscompanion_front.model.response.WeekRoutineResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.Path

interface ExerciseApi {
    @GET("/exercises/byDayRoutineId/{id}")
    suspend fun getExercisesByDayRoutineId(@Path("id") dayRoutineId: Int): List<Exercise>

    @POST("/exercises")
    suspend fun saveDayRoutine(@Body routine: DayRoutineRequest): DayRoutineResponse
}