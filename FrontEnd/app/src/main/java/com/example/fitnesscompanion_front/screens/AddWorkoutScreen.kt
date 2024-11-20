package com.example.fitnesscompanion_front.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.model.Workout
import com.example.fitnesscompanion_front.viewmodel.WorkoutViewModel

@Composable
fun AddWorkoutScreen(navController: NavController, workoutViewModel: WorkoutViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Workout") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Workout Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Duration (mins)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    // Ensure the duration is an integer
                    val durationInt = duration.toIntOrNull() ?: 0 // Default to 0 if the input is invalid
                    val newWorkout = Workout(name, durationInt)
                    workoutViewModel.addWorkout(newWorkout)
                    navController.popBackStack() // Go back to the Workout List
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Workout")
            }
        }
    }
}


