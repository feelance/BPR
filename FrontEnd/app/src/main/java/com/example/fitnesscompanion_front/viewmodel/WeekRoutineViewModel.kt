package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesscompanion_front.model.WeekRoutine
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeekRoutineViewModel : ViewModel() {
    private val _weekRoutines = MutableStateFlow<List<WeekRoutine>>(emptyList())
    val weekRoutines: StateFlow<List<WeekRoutine>> get() = _weekRoutines

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
}