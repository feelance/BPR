package com.example.fitnesscompanion_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.fitnesscompanion_front.model.Workout

class WorkoutViewModel : ViewModel() {
    // LiveData to hold the list of workouts
    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> = _workouts

    // Function to add a new workout
    fun addWorkout(workout: Workout) {
        // Get the current list of workouts, add the new workout, and update LiveData
        val currentWorkouts = _workouts.value ?: emptyList()
        _workouts.value = currentWorkouts + workout
    }

    // Initialize with some sample workouts
    init {
        _workouts.value = listOf(
            Workout("Push-ups", 15),
            Workout("Running", 30),
            Workout("Cycling", 45)
        )
    }
}