package com.example.fitnesscompanion_front.model

import android.os.Build
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

data class Exercise(
    val id:  Int = 0,
    val name: String,
    val category: String,
    val description: String
)


data class ExerciseRecord(
    val exerciseId: Int,
    val repetitions: Int,
    val weight: Float,
    val date: String = getCurrentDate() // Default to current date in yyyy-MM-dd format
)

fun getCurrentDate(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        java.time.LocalDate.now().toString()
    } else {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.format(Date())
    }
}