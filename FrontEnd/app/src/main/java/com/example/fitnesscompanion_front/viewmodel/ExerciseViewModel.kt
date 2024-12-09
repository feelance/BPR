package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.ExerciseRecord
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate
import java.util.Date

class ExerciseViewModel(private val dayRoutineId: Int) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    private val _savedRecords = MutableStateFlow<List<ExerciseRecord>>(emptyList())
    val savedRecords: StateFlow<List<ExerciseRecord>> = _savedRecords

    private val _selectedExercise = MutableStateFlow<Exercise?>(null)
    val selectedExercise: StateFlow<Exercise?> get() = _selectedExercise

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _feedbackMessage = MutableStateFlow<String?>(null)
    val feedbackMessage: StateFlow<String?> = _feedbackMessage

    init {
        if (dayRoutineId > 0) {
            fetchExercises()
        } else {
            _errorMessage.value = "Invalid Day Routine ID"
        }
    }

    fun fetchExerciseById(exerciseId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.exerciseApi.getExerciseById(exerciseId)
                _selectedExercise.value = response
                _feedbackMessage.value = "dsds"

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchExercises() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.exerciseApi.getExercisesByDayRoutineId(dayRoutineId)
                _exercises.value = response
                if (response.isNotEmpty()) {
                    _selectedExercise.value = response.first()
                    fetchExerciseRecords()
                } else {
                    _errorMessage.value = "No exercises found for this routine"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Error fetching exercises"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun fetchExerciseRecords() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = selectedExercise.value?.let {
                    RetrofitInstance.exerciseApi.getExerciseRecordsByExerciseId(
                        it.id)
                }
                if (response != null) {
                    _savedRecords.value = response
                }

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
                fetchExerciseRecords()
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Error saving exercise"
            }
        }
    }

    fun updateExercise(updatedExercise: Exercise) {
        viewModelScope.launch {
            try {
                // Call the API to update the exercise
                RetrofitInstance.exerciseApi.updateExercise(updatedExercise.id, updatedExercise)

                // Optionally refresh the list of exercises after updating
                fetchExercises()
            } catch (e: Exception) {
                e.printStackTrace() // Log the error or handle it appropriately
            }
        }
    }

    fun deleteExercise(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.exerciseApi.deleteExercise(id)
                _exercises.value = _exercises.value.filter { it.id != id }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    println("Exercise not found: $id")
                    _exercises.value = _exercises.value.filter { it.id != id }
                } else {
                    println("Http error: ${e.message()}")
                }
            } catch (e: Exception) {
                println("Unexpected error: ${e.message}")
            }
        }
    }

    fun storeExerciseRecord(exerciseId: Int, repetitions: Int, weight: Float) {
        viewModelScope.launch {
            try {
                // Create a new ExerciseRecord object
                val exerciseRecord = ExerciseRecord(
                    exerciseId = exerciseId,
                    repetitions = repetitions,
                    weight = weight
                )

                // Save the record using the repository
                RetrofitInstance.exerciseApi.saveExerciseRecord(exerciseRecord)
                fetchExerciseRecords()

                // Optionally, log success or update UI state
                _feedbackMessage.value = "Record saved successfully!"
            } catch (e: Exception) {
                // Handle any errors during the save operation
                _errorMessage.value = e.localizedMessage ?: "Failed to save record"
            }
        }
    }
    fun setSelectedExerciseByIndex(index: Int) {
        if (index in _exercises.value.indices) {
            _selectedExercise.value = _exercises.value[index]
            fetchExerciseRecords()
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


