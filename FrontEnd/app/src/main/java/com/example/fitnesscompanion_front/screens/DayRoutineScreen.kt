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
import com.example.fitnesscompanion_front.ui.theme.StyledCard
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
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

    ThemedScaffold(
        title = "Day Routines",
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navigate to Add Day Routine screen
                    navController.navigate("add_day_routine/$weekRoutineId")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Day Routines for $dayRoutineName", style = MaterialTheme.typography.h6, color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary)
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

                dayRoutines.isEmpty() -> {
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
                            navController.popBackStack()
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
                                    navController.navigate("edit_day_routine/2/2")
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
    StyledCard(
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.weight(1f)
            ) {
            Text(
                text = dayRoutineName,
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
        }
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_edit),
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }





