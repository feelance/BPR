package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(private val dayRoutineId: Int) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        if (dayRoutineId > 0) {
            fetchExercises()
        } else {
            _errorMessage.value = "Invalid Day Routine ID"
        }
    }

    fun fetchExercises() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.exerciseApi.getExercisesByDayRoutineId(dayRoutineId)
                _exercises.value = response
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Error fetching exercises"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveExercise(exercise: Exercise) {
        viewModelScope.launch {
            try {
                val request = ExerciseRequest(
                    name = exercise.name,
                    description = exercise.description,
                    category = exercise.category,
                    dayRoutineId = dayRoutineId
                )
                RetrofitInstance.exerciseApi.saveExercise(request)
                _exercises.value = _exercises.value + exercise // Update list locally
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Error saving exercise"
            }
        }
    }
}

class ExerciseViewModelFactory(private val dayRoutineId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            return ExerciseViewModel(dayRoutineId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
