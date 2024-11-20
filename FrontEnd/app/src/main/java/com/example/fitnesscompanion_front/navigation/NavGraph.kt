package com.example.fitnesscompanion_front.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnesscompanion_front.screens.AddWorkoutScreen
import com.example.fitnesscompanion_front.screens.WorkoutListScreen

sealed class Screen(val route: String) {
    object WorkoutList : Screen("workout_list")
    object AddWorkout : Screen("add_workout")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.WorkoutList.route) {
        composable(Screen.WorkoutList.route) {
            WorkoutListScreen(navController)
        }
        composable(Screen.AddWorkout.route) {
            AddWorkoutScreen(navController)
        }
    }
}