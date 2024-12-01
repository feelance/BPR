package com.example.fitnesscompanion_front.retrofit
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
import com.example.fitnesscompanion_front.model.response.GenericResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.Path

interface ExerciseApi {
    @GET("/exercises/byDayRoutineId/{id}")
    suspend fun getExercisesByDayRoutineId(@Path("id") dayRoutineId: Int): List<Exercise>

    @POST("/exercises")
    suspend fun saveExercise(@Body routine: ExerciseRequest): GenericResponse
}