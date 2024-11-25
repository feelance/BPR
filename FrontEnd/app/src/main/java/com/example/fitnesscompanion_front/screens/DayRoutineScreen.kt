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
import com.example.fitnesscompanion_front.model.DayRoutine
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DayRoutineScreen(navController: NavController, routineName: String) {
    var dayRoutines by remember { mutableStateOf<List<DayRoutine>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.dayRoutineApi.getDayRoutinesByWeekRoutineId(34)
            dayRoutines = response
            isLoading = false
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "An error occurred"
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(routineName) },
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
            Text("Day Routines for $routineName", style = MaterialTheme.typography.h6)
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
                            RoutineCard(
                                routineName = dayRoutine.name,
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
fun RoutineCard(
    routineName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() } // Allows click interaction
    ) {
        Text(
            text = routineName,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
    }
}
