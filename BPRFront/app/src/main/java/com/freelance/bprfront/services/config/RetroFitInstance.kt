package com.freelance.bprfront.services.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private const val BASE_URL = "https://tudirecciondeapi.com/"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    inline fun <T> createService(serviceClass:Class<T>) : T{
        return retrofit.create(serviceClass);
    }

}