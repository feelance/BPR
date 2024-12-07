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
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutScreen(
    navController: NavController,
    dayRoutineId: Int,
    viewModel: ExerciseViewModel = viewModel()
) {
    // State for current exercise index
    var currentExerciseIndex by remember { mutableStateOf(0) }

    // Fetch exercises for the routine
    val exercises by viewModel.exercises.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Input states for repetitions and weight
    var repetitions by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchExercises()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workout") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (exercises.isNotEmpty()) {
            val currentExercise = exercises[currentExerciseIndex]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Exercise: ${currentExercise.name}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Input fields for repetitions and weight
                OutlinedTextField(
                    value = repetitions,
                    onValueChange = { repetitions = it },
                    label = { Text("Repetitions") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (repetitions.isNotBlank() && weight.isNotBlank()) {
                            viewModel.storeExerciseRecord(
                                exerciseId = currentExercise.id,
                                repetitions = repetitions.toInt(),
                                weight = weight.toFloat()
                            )
                            repetitions = ""
                            weight = ""
                            feedbackMessage = "Record saved!"
                        } else {
                            feedbackMessage = "Please fill out both fields."
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Record")
                }

                feedbackMessage?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.body2
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Navigation buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (currentExerciseIndex > 0) {
                                currentExerciseIndex--
                                feedbackMessage = null
                                repetitions = ""
                                weight = ""
                            }
                        },
                        enabled = currentExerciseIndex > 0
                    ) {
                        Text("Previous Exercise")
                    }

                    Button(
                        onClick = {
                            if (currentExerciseIndex < exercises.lastIndex) {
                                currentExerciseIndex++
                                feedbackMessage = null
                                repetitions = ""
                                weight = ""
                            } else {
                                feedbackMessage = "Workout completed!"
                            }
                        },
                        enabled = currentExerciseIndex < exercises.lastIndex
                    ) {
                        Text("Next Exercise")
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No exercises available for this routine.")
            }
        }
    }
}
