package com.example.fitnesscompanion_front.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesscompanion_front.model.Workout
import com.example.fitnesscompanion_front.viewmodel.WorkoutViewModel
import com.example.fitnesscompanion_front.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutListScreen(navController: NavController, workoutViewModel: WorkoutViewModel = viewModel()) {
    // Observe the list of workouts from the ViewModel
    val workouts = workoutViewModel.workouts.observeAsState(emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Workout List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_workout") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Workout")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Display the list of workouts
            LazyColumn {
                items(workouts.value) { workout ->
                    Text("${workout.name} - ${workout.duration} mins", style = MaterialTheme.typography.h6)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}
