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
                Text(
                    "No routines available. Add one using the button below.",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1
                )
            } else {
                LazyColumn {
                    items(weeklyRoutines) { routine ->
                        RoutineCard(
                            routineText = routine.name,
                            onEditClick = {
                                navController.navigate("edit_week_routine/${routine.id}")
                            },
                            onDeleteClick = {
                                viewModel.deleteRoutine(routine.id)

                            },
                            onClick = {
                                navController.navigate("day_routines/${routine.id}/${routine.name}")
                            },
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
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
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

            // Text content and action buttons
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = routineText,
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
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
