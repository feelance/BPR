package com.example.fitnesscompanion_front.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.viewmodel.WeeklyRoutineViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeeklyRoutineScreen(
    navController: NavController,
    viewModel: WeeklyRoutineViewModel = viewModel() // Correct way to retrieve ViewModel
) {
    val weeklyRoutines by viewModel.weeklyRoutines.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Weekly Routine") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_routine")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Routine")
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Your Weekly Routine", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            if (weeklyRoutines.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(weeklyRoutines) { routine ->
                        RoutineCard(routineText = routine.name) {
                            navController.navigate("day_routines/${routine.name}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RoutineCard(routineText: String, onClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = routineText, style = MaterialTheme.typography.body1)
        }
    }
}



