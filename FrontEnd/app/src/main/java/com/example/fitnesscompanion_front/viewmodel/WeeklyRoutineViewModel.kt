package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesscompanion_front.model.WeeklyRoutine
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeeklyRoutineViewModel : ViewModel() {
    private val _weeklyRoutines = MutableStateFlow<List<WeeklyRoutine>>(emptyList())
    val weeklyRoutines: StateFlow<List<WeeklyRoutine>> get() = _weeklyRoutines

    init {
        fetchWeeklyRoutines()
    }

    private fun fetchWeeklyRoutines() {
        viewModelScope.launch {
            try {
                val routines = RetrofitInstance.api.getWeeklyRoutines()
                _weeklyRoutines.value = routines
            } catch (e: Exception) {
                e.printStackTrace() // Handle errors (e.g., show a message to the user)
            }
        }
    }
}