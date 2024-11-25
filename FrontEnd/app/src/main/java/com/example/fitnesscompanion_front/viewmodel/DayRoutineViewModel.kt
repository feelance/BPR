package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class DayRoutineViewModel : ViewModel() {

    private val _dayRoutines = MutableStateFlow<List<DayRoutine>>(emptyList())
    val dayRoutines: StateFlow<List<DayRoutine>> = _dayRoutines

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchDayRoutines(weekRoutineId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.dayRoutineApi.getDayRoutinesByWeekRoutineId(36)
                _dayRoutines.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred"
                _isLoading.value = false
            }
        }
    }

    fun saveDayRoutine(name: String, weekRoutineId: Int) {
        viewModelScope.launch {
            try {
                // Call the API
                val request = DayRoutineRequest(name = name, weekRoutineId = weekRoutineId)
                RetrofitInstance.dayRoutineApi.saveDayRoutine(request)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}