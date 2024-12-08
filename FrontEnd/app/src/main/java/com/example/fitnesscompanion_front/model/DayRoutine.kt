package com.example.fitnesscompanion_front.model

data class DayRoutine (
    val id: Int = 0,
    val exercises:List<Exercise> = emptyList(),
    val weekRoutineId : Int,
    val name : String,
    val notes:String = ""
)
