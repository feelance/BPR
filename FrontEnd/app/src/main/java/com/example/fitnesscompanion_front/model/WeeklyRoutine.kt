package com.example.fitnesscompanion_front.model

data class WeeklyRoutine(
    val id: Int,
    val name: String,
    val notes: String,
    val dayRoutines: List<DayRoutine>
)