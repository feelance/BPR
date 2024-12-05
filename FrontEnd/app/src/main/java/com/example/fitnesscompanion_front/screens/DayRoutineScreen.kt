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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DayRoutineScreen(
    navController: NavController,
    dayRoutineName: String,
    weekRoutineId: Int,
    viewModel: DayRoutineViewModel = viewModel()
) {
    // Observe ViewModel state
    val dayRoutines by viewModel.dayRoutines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    // Fetch data when screen starts
    LaunchedEffect(Unit) {
        viewModel.fetchDayRoutines(weekRoutineId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Day Routines") },
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
                    // Handle FAB click, e.g., navigate to an Add Day Routine screen
                    navController.navigate("add_day_routine/$weekRoutineId")
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
            Text("Day Routines for $dayRoutineName", style = MaterialTheme.typography.h6)
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

                dayRoutines.isEmpty() -> { // Check if the list is empty
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No routines found!",
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
                    LazyColumn {
                        items(dayRoutines) { dayRoutine ->
                            DayRoutineCard(
                                dayRoutineName = dayRoutine.name,
                                onEditClick = {
                                    navController.navigate("edit_week_routine/${dayRoutine.id}")
                                },
                                onDeleteClick = {
                                    viewModel.deleteDayRoutine(dayRoutine.id)

                                },
                                onClick = {
                                    navController.navigate("exercises/${dayRoutine.id}/${dayRoutine.name}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DayRoutineCard(
    dayRoutineName: String,
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
                    .clickable { onClick() }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dayRoutineName,
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
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


