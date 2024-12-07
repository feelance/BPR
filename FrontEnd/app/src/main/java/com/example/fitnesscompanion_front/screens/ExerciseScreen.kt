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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModel
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciseScreen(
    navController: NavController,
    dayRoutineName: String,
    dayRoutineId: Int,
    viewModel: ExerciseViewModel = viewModel()
) {
    // Observe ViewModel state
    val exercises by viewModel.exercises.collectAsState()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    // Fetch data when screen starts
    LaunchedEffect(Unit) {
        viewModel.fetchExercises()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exercises") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_exercise/$dayRoutineId")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Error",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.error
                    )
                }

                exercises.isEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No exercises yet!",
                            style = MaterialTheme.typography.body1
                        )
                        Button(onClick = {
                            // Handle retry or navigation back
                        }) {
                            Text("Go Back")
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f) // Adjust height to leave space for the button
                    ) {
                        items(exercises) { exercise ->
                            ExerciseCard(
                                exerciseName = exercise.name,
                                onEditClick = {
                                    navController.navigate("edit_exercise/${exercise.id}")
                                },
                                onDeleteClick = {
                                    viewModel.deleteExercise(exercise.id)
                                }
                            )
                        }
                    }
                }
            }

            // "Start Workout" button at the bottom
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("workout_screen/$dayRoutineId")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Start Workout")
            }
        }
    }
}



@Composable
fun ExerciseCard(
    exerciseName: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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

            // Text content and action buttons
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = exerciseName,
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
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

