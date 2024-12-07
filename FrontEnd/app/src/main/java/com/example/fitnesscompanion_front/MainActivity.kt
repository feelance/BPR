package com.example.fitnesscompanion_front


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.fitnesscompanion_front.navigation.AppNavigation
import com.example.fitnesscompanion_front.screens.AddWeekRoutineScreen
import com.example.fitnesscompanion_front.screens.DayRoutineScreen
import com.example.fitnesscompanion_front.screens.HomeScreen
import com.example.fitnesscompanion_front.screens.LoginScreen
import com.example.fitnesscompanion_front.screens.WeekRoutineScreen
import com.example.fitnesscompanion_front.ui.theme.BlackBackground
import com.example.fitnesscompanion_front.ui.theme.FitnessCompanionFrontTheme
import com.example.fitnesscompanion_front.ui.theme.LightGreySurface
import com.example.fitnesscompanion_front.ui.theme.PrimaryGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessCompanionFrontTheme {
                // Use a Surface to set a global background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background // Use the background from your theme
                ) {
                    AppNavigation()
                }
            }
        }
    }
}




@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = BlackBackground,  // Set the background to black
        elevation = 12.dp  // Added subtle shadow for a floating effect
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)  // Light grey for unselected
                )
            },
            selected = false,  // You can dynamically control this with state
            onClick = { navController.navigate(Screen.Home.route) },
            selectedContentColor = PrimaryGrey,  // Use a grey color for selected icon
            unselectedContentColor = LightGreySurface  // Lighter grey for unselected
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Weekly Routine",
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)  // Same for unselected
                )
            },
            selected = false,  // You can dynamically control this with state
            onClick = { navController.navigate(Screen.WeeklyRoutine.route) },
            selectedContentColor = PrimaryGrey,  // Grey color for selected icon
            unselectedContentColor = LightGreySurface  // Lighter grey for unselected
        )
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object WeeklyRoutine : Screen("weekly_routine")
}
