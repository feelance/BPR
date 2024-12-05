package com.example.fitnesscompanion_front.retrofit
import androidx.tracing.perfetto.handshake.protocol.Response
import com.example.fitnesscompanion_front.model.UpdateWeekRoutineRequest
import retrofit2.http.GET

import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.response.GenericResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WeekRoutineApi {
    @GET("/weekRoutines/user/{id}")
    suspend fun getWeeklyRoutines(@Path("id") userId: Int): List<WeekRoutine>

    @POST("/weekRoutines") // Replace with your actual API endpoint
    suspend fun saveWeekRoutine(@Body routine: WeekRoutineRequest): GenericResponse

    @GET("/weekRoutines/{id}")
    suspend fun getWeekRoutineById(@Path("id") userId: Int): WeekRoutine

    @PUT("/weekRoutines/{id}")
    suspend fun updateWeekRoutine(@Path("id") weekRoutineId: Int, @Body weekRoutineRequest: UpdateWeekRoutineRequest): WeekRoutine

    @DELETE("/weekRoutines/{id}")
    suspend fun deleteWeekRoutine(@Path("id") routineId: Int): Response
}