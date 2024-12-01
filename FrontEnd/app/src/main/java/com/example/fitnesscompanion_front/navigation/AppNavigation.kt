package com.example.fitnesscompanion_front.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesscompanion_front.BottomNavigationBar
import com.example.fitnesscompanion_front.Screen
import com.example.fitnesscompanion_front.screens.AddDayRoutineScreen
import com.example.fitnesscompanion_front.screens.AddWeekRoutineScreen
import com.example.fitnesscompanion_front.screens.DayRoutineScreen
import com.example.fitnesscompanion_front.screens.HomeScreen
import com.example.fitnesscompanion_front.screens.LoginScreen
import com.example.fitnesscompanion_front.screens.WeekRoutineScreen
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModel
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var isAuthenticated by remember { mutableStateOf(false) }

    // Set up the Scaffold with BottomNavigation
    Scaffold(
        bottomBar = {
            if (isAuthenticated){
                BottomNavigationBar(navController) }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isAuthenticated) Screen.Home.route else "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        isAuthenticated = true
                        navController.navigate(Screen.Home.route) {
                            popUpTo("login") { inclusive = true } // Clear login from the backstack
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.WeeklyRoutine.route) {
                WeekRoutineScreen(navController = navController)
            }
            composable(
                route = "day_routines/{weekRoutineId}/{routineName}",
                arguments = listOf(
                    navArgument("routineName") { type = NavType.StringType },
                    navArgument("weekRoutineId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val routineName = backStackEntry.arguments?.getString("routineName") ?: "Unknown Routine"
                val weekRoutineId = backStackEntry.arguments?.getInt("weekRoutineId") ?: 0

                val viewModel: DayRoutineViewModel = viewModel(factory = DayRoutineViewModelFactory(weekRoutineId))
                DayRoutineScreen(navController, routineName, weekRoutineId)
            }
            composable("add_week_routine") { AddWeekRoutineScreen(navController, viewModel()) }

            composable(
                route = "add_day_routine/{weekRoutineId}",
                arguments = listOf(
                    navArgument("weekRoutineId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val weekRoutineId = backStackEntry.arguments?.getInt("weekRoutineId") ?: 0
                val viewModel: DayRoutineViewModel = viewModel(factory = DayRoutineViewModelFactory(weekRoutineId))
                AddDayRoutineScreen(navController, weekRoutineId, viewModel)
            }
            composable(
                route = "exercises/{dayRoutineId}/{dayRoutineName}",
                arguments = listOf(
                    navArgument("dayRoutineName") { type = NavType.StringType },
                    navArgument("dayRoutineId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val dayRoutineName = backStackEntry.arguments?.getString("dayRoutineName") ?: "Unknown Day Routine"
                val dayRoutineId = backStackEntry.arguments?.getInt("dayRoutineId") ?: 0

                val viewModel: DayRoutineViewModel = viewModel(factory = ExerciseViewModelFactory(dayRoutineId))
                ExerciseScreen(navController, dayRoutineName, dayRoutineId)
            }

        }
    }
}