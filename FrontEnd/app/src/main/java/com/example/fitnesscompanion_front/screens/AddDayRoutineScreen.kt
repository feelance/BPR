package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.fitnesscompanion_front.model.DayRoutine

import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddDayRoutineScreen(
    navController: NavController,
    weekRoutineId: Int,
    viewModel: DayRoutineViewModel = viewModel()
) {
    var dayRoutineName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    ThemedScaffold(
        title = "Add Day Routine",
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Input field for Day Routine Name
            TextField(
                value = dayRoutineName,
                onValueChange = { dayRoutineName = it },
                label = { Text("Day Routine Name") },
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

            // Input field for Notes
            TextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (Optional)") },
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

            // Save Button
            Button(
                onClick = {
                    if (dayRoutineName.isNotBlank()) {
                        val dayRoutine = DayRoutine(
                            name = dayRoutineName,
                            weekRoutineId = weekRoutineId,
                            notes = notes
                        )
                        viewModel.saveDayRoutine(dayRoutine, weekRoutineId)
                        navController.popBackStack()
                    } else {
                        // Handle empty input
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Color.Blue, // Change button background color
                    contentColor = Color.White // Change text color on the button
                ),
                shape = MaterialTheme.shapes.medium // Customize the button shape
            ) {
                Text("Save")
            }
        }
    }
}


