package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesscompanion_front.viewmodel.WeekRoutineViewModel
import kotlinx.coroutines.launch

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditWeekRoutineScreen(
    navController: NavController,
    routineId: Int,
    viewModel: WeekRoutineViewModel = viewModel()
) {
    // States for user input
    var routineName by remember { mutableStateOf("") }
    var routineNotes by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // Fetch the routine details on screen load
    LaunchedEffect(routineId) {
        isLoading = true
        try {
            val routine = viewModel.getWeekRoutineById(routineId)
            routineName = routine.name // Initialize the name field
            routineNotes = routine.notes // Initialize the notes field
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "Failed to load routine details"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Week Routine") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Edit Week Routine Details", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                // Text field for routine name
                OutlinedTextField(
                    value = routineName,
                    onValueChange = { routineName = it },
                    label = { Text("Routine Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Text field for routine notes
                OutlinedTextField(
                    value = routineNotes,
                    onValueChange = { routineNotes = it },
                    label = { Text("Routine Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Save button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                viewModel.updateWeekRoutine(
                                    id = routineId,
                                    name = routineName,
                                    notes = routineNotes
                                )
                                navController.navigate(Screen.WeeklyRoutine.route) {
                                    popUpTo(Screen.WeeklyRoutine.route) { inclusive = true }
                                }
                            } catch (e: Exception) {
                                errorMessage = e.localizedMessage ?: "Failed to save changes"
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            }

            // Show error message
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

