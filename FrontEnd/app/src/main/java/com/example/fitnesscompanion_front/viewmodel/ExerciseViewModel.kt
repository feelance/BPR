package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class ExerciseViewModel(private val dayRoutineId: Int) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchExercises(dayRoutineId)
    }

    fun fetchExercises(dayR: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.exerciseApi.getDayRoutinesByWeekRoutineId(dayRoutineId)
                _exercises.value = response
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
                val request = ExerciseRequest(name = dayRoutine.name, weekRoutineId = weekRoutineId)
                RetrofitInstance.exerciseApi.saveDayRoutine(request)
                _exercises.value = RetrofitInstance.exerciseApi.getDayRoutinesByWeekRoutineId(weekRoutineId)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
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
