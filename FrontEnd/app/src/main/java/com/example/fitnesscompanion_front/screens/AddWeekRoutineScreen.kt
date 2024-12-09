package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnesscompanion_front.viewmodel.WeekRoutineViewModel
import kotlinx.coroutines.launch

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddWeekRoutineScreen(
    navController: NavController,
    viewModel: WeekRoutineViewModel = viewModel()
) {
    // States for user input
    var routineName by remember { mutableStateOf("") }
    var routineNotes by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    ThemedScaffold(
        title = "Add Week Routine",
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
            Text(
                text = "Edit Week Routine Details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text field for routine name
            OutlinedTextField(
                value = routineName,
                onValueChange = { routineName = it },
                label = { Text("Routine Name") },
                modifier = Modifier.fillMaxWidth(),
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

            // Text field for routine notes
            OutlinedTextField(
                value = routineNotes,
                onValueChange = { routineNotes = it },
                label = { Text("Routine Notes") },
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

            // Save button
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                // Call ViewModel or API
                                viewModel.saveWeekRoutine(
                                    name = routineName,
                                    notes = routineNotes
                                )
                                navController.navigate(Screen.WeeklyRoutine.route) {
                                    popUpTo(Screen.WeeklyRoutine.route) { inclusive = true }
                                }
                            } catch (e: Exception) {
                                errorMessage = e.localizedMessage ?: "An error occurred"
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
                    Text("Save Routine")
                }
            }

            // Show error message
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
