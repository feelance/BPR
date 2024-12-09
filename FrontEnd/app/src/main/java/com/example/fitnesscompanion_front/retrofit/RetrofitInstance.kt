package com.example.fitnesscompanion_front.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080"

    val weekRoutineApi: WeekRoutineApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeekRoutineApi::class.java)
    }

    val dayRoutineApi: DayRoutineApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DayRoutineApi::class.java)
    }

    val exerciseApi: ExerciseApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseApi::class.java)
    }


}

object RetrofitTest {
    var exerciseApi: ExerciseApi = createRetrofitInstance().create(ExerciseApi::class.java)
    var dayRoutineApi: DayRoutineApi = createRetrofitInstance().create(DayRoutineApi::class.java)
    var weekRoutineApi: WeekRoutineApi = createRetrofitInstance().create(WeekRoutineApi::class.java)
}

fun createRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create()) // For JSON parsing
        .build()
}
