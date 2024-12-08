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
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel(),
    dayRoutineId: Int
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    ThemedScaffold(
        title = "Add Exercise",
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

            // TextField for Description
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    if (name.isNotBlank() && category.isNotBlank()) {
                        val exercise = Exercise(
                            name = name,
                            category = category,
                            description = description
                        )
                        viewModel.saveExercise(exercise)
                        navController.popBackStack()
                    } else {
                        // Handle empty input (e.g., show a Snackbar or error message)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}

