package com.example.fitnesscompanion_front.retrofit
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.UpdateWeekRoutineRequest
import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.model.request.UpdateDayRoutineRequest
import com.example.fitnesscompanion_front.model.response.GenericResponse
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

import retrofit2.http.Path

interface DayRoutineApi {
    @GET("/dayRoutines/byWeekRoutine/{id}")
    suspend fun getDayRoutinesByWeekRoutineId(@Path("id") weekRoutineId: Int): List<DayRoutine>

    @GET("/dayRoutines/{id}")
    suspend fun getDayRoutineById(@Path("id") id: Int): DayRoutine

    @POST("/dayRoutines")
    suspend fun saveDayRoutine(@Body routine: DayRoutineRequest): GenericResponse

    @DELETE("/dayRoutines/{id}")
    suspend  fun deleteDayRoutine(@Path("id") id: Int): ResponseBody

    @PUT("/dayRoutines/{id}")
    suspend fun updateDayRoutine(@Path("id") dayRoutineId: Int, @Body dayRoutineRequest: UpdateDayRoutineRequest): DayRoutine
}