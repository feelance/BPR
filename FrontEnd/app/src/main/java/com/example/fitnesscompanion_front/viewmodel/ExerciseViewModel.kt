package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.WeekRoutine
import com.example.fitnesscompanion_front.model.WeekRoutineRequest
import com.example.fitnesscompanion_front.model.request.DayRoutineRequest
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
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
                val response = RetrofitInstance.exerciseApi.getExercisesByDayRoutineId(dayRoutineId)
                _exercises.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred"
                _isLoading.value = false
            }
        }
    }

    fun saveDayRoutine(exercise: Exercise) {
        viewModelScope.launch {
            try {
                // Call the API
                val request = ExerciseRequest(name = exercise.name, description = exercise.description, category = exercise.category, dayRoutineId)
                RetrofitInstance.exerciseApi.saveExercise(request)
                _exercises.value = RetrofitInstance.exerciseApi.getExercisesByDayRoutineId(dayRoutineId)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }


}


class ExerciseViewModelFactory(private val dayRoutineId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DayRoutineViewModel::class.java)) {
            return DayRoutineViewModel(dayRoutineId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
