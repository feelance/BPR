package com.freelance.bprfront.services.client

import com.freelance.bprfront.model.WeekRoutine
import com.freelance.bprfront.services.api.WeekRoutines
import com.freelance.bprfront.services.config.RetrofitInstance
import retrofit2.Response

object WeekRoutinesClient {
    suspend fun getAllRoutines(): Response<WeekRoutine> {
        return RetrofitInstance.createService(WeekRoutines::class.java).getAllRoutines()
    }
}