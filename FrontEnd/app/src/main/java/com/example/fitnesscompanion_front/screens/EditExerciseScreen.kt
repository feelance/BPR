package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.request.ExerciseRequest
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
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

    // Use ThemedScaffold instead of Scaffold
    ThemedScaffold(
        title = "Edit Exercise",
        navController = navController,
        showBackButton = true,
        floatingActionButton = null
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // TextField for Exercise Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Exercise Name") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.White,
                    errorCursorColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField for Exercise Category
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Exercise Category") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.White,
                    errorCursorColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField for Exercise Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.White,
                    errorCursorColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Update Button with custom colors
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
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue, // Custom background color
                    contentColor = Color.White // Custom text color
                )
            ) {
                Text("Update")
            }
        }
    }
}
