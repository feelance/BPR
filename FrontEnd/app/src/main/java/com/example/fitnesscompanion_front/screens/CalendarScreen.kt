package com.example.fitnesscompanion_front.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    workouts: List<LocalDate> // List of workout dates
) {
    val currentMonth = YearMonth.now() // Get the current month and year
    val firstDayOfMonth = currentMonth.atDay(1) // First day of the current month
    val daysInMonth = currentMonth.lengthOfMonth() // Number of days in the month
    val firstDayOfWeek = (firstDayOfMonth.dayOfWeek.value + 6) % 7 // 0 = Monday, 1 = Tuesday, ..., 6 = Sunday

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)) // Dark background for better contrast
            .padding(16.dp)
    ) {
        // Header with month name
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                    " ${currentMonth.year}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp),
            color = Color.White // White text for contrast
        )

        // Weekday labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun").forEach { day ->
                Text(
                    text = day,
                    fontSize = 14.sp,
                    color = Color.LightGray, // Light gray for labels
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(7), // 7 columns for 7 days in a week
            modifier = Modifier.fillMaxSize(),
            content = {
                // Add empty cells for the first week if the month doesn't start on Sunday
                if (firstDayOfWeek != 0) {
                    items(firstDayOfWeek) {
                        Box(modifier = Modifier.size(48.dp)) // Empty space for days before day 1
                    }
                }

                // Add days of the month
                items(daysInMonth) { day ->
                    val date = firstDayOfMonth.plusDays(day.toLong())
                    val hasWorkout = workouts.contains(date)

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = (day + 1).toString(), // Correctly start days from 1
                                fontSize = 14.sp,
                                color = Color.White // White text for visibility
                            )
                            if (hasWorkout) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Surface(
                                    modifier = Modifier.size(8.dp),
                                    shape = CircleShape,
                                    color = Color.Green // Green dot for workouts
                                ) {}
                            }
                        }
                    }
                }
            }
        )
    }
}


