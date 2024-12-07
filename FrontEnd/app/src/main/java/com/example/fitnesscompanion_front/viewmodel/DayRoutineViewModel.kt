package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.UpdateWeekRoutineRequest
import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.model.request.UpdateDayRoutineRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class DayRoutineViewModel(private val weekRoutineId: Int) : ViewModel() {

    private val _dayRoutines = MutableStateFlow<List<DayRoutine>>(emptyList())
    val dayRoutines: StateFlow<List<DayRoutine>> = _dayRoutines

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchDayRoutines(weekRoutineId)
    }

    fun fetchDayRoutines(weekRoutineId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.dayRoutineApi.getDayRoutinesByWeekRoutineId(weekRoutineId)
                _dayRoutines.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred"
                _isLoading.value = false
            }
        }
    }

    fun saveDayRoutine(dayRoutine: DayRoutine, weekRoutineId: Int) {
        viewModelScope.launch {
            try {
                // Call the API
                val request = DayRoutineRequest(name = dayRoutine.name, weekRoutineId = weekRoutineId)
                RetrofitInstance.dayRoutineApi.saveDayRoutine(request)
                _dayRoutines.value = RetrofitInstance.dayRoutineApi.getDayRoutinesByWeekRoutineId(weekRoutineId)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    // Delete a Day Routine
    fun deleteDayRoutine(dayRoutineId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.dayRoutineApi.deleteDayRoutine(dayRoutineId)
                _dayRoutines.value = _dayRoutines.value.filter { it.id != dayRoutineId }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Failed to delete day routine"
            }
        }
    }

    suspend fun getDayRoutineById(id: Int): DayRoutine {
        return try {
            // Call the API and return the result
            RetrofitInstance.dayRoutineApi.getDayRoutineById(id)
        } catch (e: Exception) {
            println("Error fetching routine: ${e.message}")
            throw e // Rethrow the error for the caller to handle
        }
    }

    suspend fun updateDayRoutine(dayRoutineId: Int, name: String,routineNotes: String) {
        try {
            val request = UpdateDayRoutineRequest(name = name,notes = routineNotes)
            RetrofitInstance.dayRoutineApi.updateDayRoutine(dayRoutineId,request)
            fetchDayRoutines(dayRoutineId)
        } catch (e: Exception) {
            throw e // Handle or log the error
        }
    }


}


class DayRoutineViewModelFactory(private val weekRoutineId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DayRoutineViewModel::class.java)) {
            return DayRoutineViewModel(weekRoutineId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
