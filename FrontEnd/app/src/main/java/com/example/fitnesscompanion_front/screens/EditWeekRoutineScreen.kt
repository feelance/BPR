package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
import com.example.fitnesscompanion_front.viewmodel.WeekRoutineViewModel
import kotlinx.coroutines.launch
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditWeekRoutineScreen(
    navController: NavController,
    routineId: Int,
    viewModel: WeekRoutineViewModel = viewModel()
) {
    // States for text inputs and loading
    var routineName by remember { mutableStateOf("") }
    var routineNotes by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // Fetch routine details when the screen loads
    LaunchedEffect(routineId) {
        isLoading = true
        try {
            val routine = viewModel.getWeekRoutineById(routineId)
            routineName = routine.name
            routineNotes = routine.notes
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "Failed to load routine details"
        } finally {
            isLoading = false
        }
    }

    // Themed scaffold with top bar and optional back navigation
    ThemedScaffold(
        title = "Edit Week Routine",
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
            // Title
            Text(
                text = "Edit Week Routine Details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Show loading indicator if fetching data
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                // Routine Name Text Input
                OutlinedTextField(
                    value = routineName,
                    onValueChange = { routineName = it },
                    label = { Text("Routine Name") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
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

                // Routine Notes Text Input
                OutlinedTextField(
                    value = routineNotes,
                    onValueChange = { routineNotes = it },
                    label = { Text("Routine Notes", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
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

                // Save Button
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
                                navController.popBackStack() // Navigate back after saving
                            } catch (e: Exception) {
                                errorMessage = e.localizedMessage ?: "Failed to save changes"
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = Color.Blue, // Change button background color
                        contentColor = Color.White // Change text color on the button
                    ),
                    shape = MaterialTheme.shapes.medium // Customize the button shape

                ) {
                    Text("Save Changes")
                }
            }

            // Display error message if any
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



