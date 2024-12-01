package com.example.fitnesscompanion_front.model.request

data class ExerciseRequest(
    var name: String,
    var description: String,
    var category: String,
    var dayRoutineId: Int
)
