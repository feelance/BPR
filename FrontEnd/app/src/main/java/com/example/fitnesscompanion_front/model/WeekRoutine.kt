package com.example.fitnesscompanion_front.model

data class WeekRoutine(
    val id: Int = 1,
    val name: String = "",
    val notes: String = "",
    val dayRoutines: List<DayRoutine> = emptyList(),
    val userId: Int = 1
)

data class WeekRoutineRequest(
    val name: String,
    val notes: String,
    val userId: Int
)