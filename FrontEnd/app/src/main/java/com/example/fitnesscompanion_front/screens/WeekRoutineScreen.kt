package com.example.fitnesscompanion_front.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.viewmodel.WeekRoutineViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesscompanion_front.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeekRoutineScreen(
    navController: NavController,
    viewModel: WeekRoutineViewModel = viewModel() // Correct way to retrieve ViewModel
) {
    val weeklyRoutines by viewModel.weekRoutines.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Week Routines") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_week_routine")
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
                        RoutineCard(
                            routineText = routine.name,
                            onClick = { navController.navigate("day_routines/${routine.id}/${routine.name}")},
                            backgroundImageRes = R.drawable.gym_logo
                        )
                        }
                    }
                }
            }
        }
    }


@Composable
fun RoutineCard(
    routineText: String,
    onClick: () -> Unit,
    backgroundImageRes: Int // Add a parameter for the background image resource
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        backgroundColor = Color.DarkGray // Set dark background for fallback
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Set a fixed height for the card
        ) {
            // Background Image
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Dark overlay for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            // Text content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = routineText,
                    style = MaterialTheme.typography.h6,
                    color = Color.White // White text for contrast
                )
            }
        }
    }
}



