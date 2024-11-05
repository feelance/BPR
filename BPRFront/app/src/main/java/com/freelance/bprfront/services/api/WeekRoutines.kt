package com.freelance.bprfront.services.api

import com.freelance.bprfront.model.WeekRoutine
import retrofit2.Response
import retrofit2.http.GET

interface WeekRoutines {
    @GET("getallroutines")
    suspend fun getAllRoutines(): Response<WeekRoutine>
}