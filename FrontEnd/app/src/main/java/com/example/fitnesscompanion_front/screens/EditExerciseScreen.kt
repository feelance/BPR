package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel(),
    exerciseId: Int // ID of the exercise to be edited
) {
    // Observe the selectedExercise StateFlow from the ViewModel
    val selectedExercise by viewModel.selectedExercise.collectAsState()

    // UI state variables
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isInitialized by remember { mutableStateOf(false) }

    // Fetch the exercise details once
    LaunchedEffect(exerciseId) {
        if (!isInitialized) {
            viewModel.fetchExerciseById(exerciseId)
        }
    }

    // Populate fields when the exercise is loaded
    LaunchedEffect(selectedExercise) {
        selectedExercise?.let { exercise ->
            if (!isInitialized) {
                name = exercise.name
                category = exercise.category
                description = exercise.description
                isInitialized = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Exercise") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // TextField for Exercise Name
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Exercise Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField for Exercise Category
            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Exercise Category") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField for Exercise Description
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Update Button
            Button(
                onClick = {
                    if (name.isNotBlank() && category.isNotBlank()) {
                        val updatedExercise = Exercise(
                            id = exerciseId,
                            name = name,
                            category = category,
                            description = description
                        )
                        viewModel.updateExercise(updatedExercise)
                        navController.popBackStack()
                    } else {
                        // Handle empty input (e.g., show a Snackbar or error message)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Update")
            }
        }
    }
}
