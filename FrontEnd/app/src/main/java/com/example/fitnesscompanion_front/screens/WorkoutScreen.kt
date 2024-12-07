package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.model.ExerciseRecord
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutScreen(
    navController: NavController,
    dayRoutineId: Int,
    viewModel: ExerciseViewModel = viewModel()
) {
    var currentExerciseIndex by remember { mutableStateOf(0) }

    // Fetch exercises for the routine
    val exercises by viewModel.exercises.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val savedRecords by viewModel.savedRecords.collectAsState()

    var repetitions by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchExercises()
        viewModel.fetchExerciseRecords()
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

                Spacer(modifier = Modifier.height(24.dp))

                // List of saved exercise records in a LazyColumn
                Text(
                    text = "Saved Records:",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (savedRecords.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), // Makes it scrollable within the remaining space
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(savedRecords.filter { it.exerciseId == currentExercise.id }) { record ->
                            ExerciseRecordCard(record = record)
                        }
                    }
                } else {
                    Text("No records saved for this exercise.")
                }

                // Navigation buttons
                Spacer(modifier = Modifier.height(16.dp))
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


@Composable
fun ExerciseRecordCard(
    record: ExerciseRecord,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        backgroundColor = Color.DarkGray
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

            // Dark overlay for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Exercise details
                Column {
                    Text(
                        text = "Reps: ${record.repetitions}",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                    Text(
                        text = "Weight: ${record.weight} kg",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                    Text(
                        text = "Date: ${record.date}",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }

                // Edit and Delete buttons
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_edit), // Replace with your edit icon
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_delete), // Replace with your delete icon
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}




