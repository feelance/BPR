package com.example.fitnesscompanion_front.model

data class Exercise(
    val id:  Int = 0,
    val name: String,
    val category: String,
    val description: String
)


data class ExerciseRecord(
    val exerciseId: Int,
    val repetitions: Int,
    val weight: Float
)
