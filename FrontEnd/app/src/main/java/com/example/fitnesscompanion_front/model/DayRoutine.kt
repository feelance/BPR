package com.example.fitnesscompanion_front.model

data class DayRoutine (
    val id: Long,
    val name: String,
    val exercises: List<Exercise>
)