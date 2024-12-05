package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesscompanion_front.model.WeekRoutine
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.UpdateWeekRoutineRequest
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeekRoutineViewModel : ViewModel() {
    private val _weekRoutines = MutableStateFlow<List<WeekRoutine>>(emptyList())
    val weekRoutines: StateFlow<List<WeekRoutine>> get() = _weekRoutines

    private val _weekRoutine = MutableStateFlow(WeekRoutine())
    val weekRoutine: StateFlow<WeekRoutine> get() = _weekRoutine

    init {
        fetchWeekRoutines()
    }

    private fun fetchWeekRoutines() {
        viewModelScope.launch {
            try {
                val routines = RetrofitInstance.weekRoutineApi.getWeeklyRoutines(1)
                _weekRoutines.value = routines
            } catch (e: Exception) {
                e.printStackTrace() // Handle errors (e.g., show a message to the user)
            }
        }
    }

    fun saveWeekRoutine(name: String, notes: String) {
        viewModelScope.launch {
            try {
                // Call the API
                val request = WeekRoutineRequest(name = name, notes = notes, userId = 1)
                RetrofitInstance.weekRoutineApi.saveWeekRoutine(request)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    suspend fun getWeekRoutineById(id: Int): WeekRoutine {
        return try {
            // Call the API and return the result
            RetrofitInstance.weekRoutineApi.getWeekRoutineById(id)
        } catch (e: Exception) {
            println("Error fetching routine: ${e.message}")
            throw e // Rethrow the error for the caller to handle
        }
    }


    suspend fun updateWeekRoutine(id: Int, name: String, notes: String) {
        try {
            val request = UpdateWeekRoutineRequest(name = name, notes = notes, userId = 1)
            RetrofitInstance.weekRoutineApi.updateWeekRoutine(id,request)
            fetchWeekRoutines()
        } catch (e: Exception) {
            throw e // Handle or log the error
        }
    }

    fun deleteRoutine(routineId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.weekRoutineApi.deleteWeekRoutine(routineId)
                _weekRoutines.value = _weekRoutines.value.filter { it.id != routineId }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    println("Routine not found: $routineId")
                    _weekRoutines.value = _weekRoutines.value.filter { it.id != routineId }
                } else {
                    println("Http error: ${e.message()}")
                }
            } catch (e: Exception) {
                println("Unexpected error: ${e.message}")
            }
        }
    }

}

