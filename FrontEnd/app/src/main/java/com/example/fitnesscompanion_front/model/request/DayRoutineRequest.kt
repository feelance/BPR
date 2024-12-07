package com.example.fitnesscompanion_front.model.request

data class DayRoutineRequest(
    var name: String,
    var weekRoutineId: Int)

data class UpdateDayRoutineRequest(
   var name: String,
   var notes: String
)


