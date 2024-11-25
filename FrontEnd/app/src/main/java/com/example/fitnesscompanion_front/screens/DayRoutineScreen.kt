package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val dayRoutines by viewModel.dayRoutines.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    // Fetch data when screen starts
    LaunchedEffect(Unit) {
        viewModel.fetchDayRoutines(weekRoutineId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(dayRoutineName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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

                else -> {
                    LazyColumn {
                        items(dayRoutines) { dayRoutine ->
                            DayRoutineCard(
                                dayRoutineName = dayRoutine.name,
                                onClick = {
                                    // Handle click event, e.g., navigate to a details screen
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
    onClick: () -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = dayRoutineName,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
    }
}

