package com.example.fitnesscompanion_front.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4E342E), // Dark Brown
    secondary = Color(0xFF6D4C41), // Muted Brown
    tertiary = Color(0xFFA1887F), // Soft Accent
    background = Color(0xFF2E2E2E), // Dark Grey
    surface = Color(0xFF3E3E3E), // Slightly lighter surface
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFFBE9E7), // Light contrast for text
    onSurface = Color(0xFFFBE9E7) // Light contrast for text
)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E1E2F), // Dark slate for primary elements
    onPrimary = Color(0xFFE0E0E0), // Light gray text/icons on dark slate
    primaryContainer = Color(0xFF2D2D44), // Slightly lighter slate for containers
    onPrimaryContainer = Color(0xFFD6D6D6), // Medium-light gray text/icons on slate

    background = Color(0xFF121212), // True black for the background
    onBackground = Color(0xFFE0E0E0), // Light gray text/icons on black

    surface = Color(0xFF1C1C1C), // Dark charcoal for surfaces
    onSurface = Color(0xFFCFCFCF), // Medium-light gray text/icons on dark surfaces

    secondary = Color(0xFF4A4A72), // Muted blue-gray for secondary elements
    onSecondary = Color(0xFFE0E0E0), // Light gray text/icons on blue-gray
    secondaryContainer = Color(0xFF3A3A52), // Even darker blue-gray for containers
    onSecondaryContainer = Color(0xFFDADADA), // Light gray on blue-gray

    tertiary = Color(0xFF616161), // Neutral gray for tertiary accents
    onTertiary = Color(0xFFE0E0E0), // Light gray text/icons on neutral gray
    tertiaryContainer = Color(0xFF2E2E2E), // Dark neutral gray for tertiary containers
    onTertiaryContainer = Color(0xFFD0D0D0), // Medium-light gray on dark gray

    error = Color(0xFFD32F2F), // Professional red for errors
    onError = Color.White, // White text/icons on error red
    errorContainer = Color(0xFF4A2020), // Muted dark red for error containers
    onErrorContainer = Color(0xFFE57373) // Light red text/icons on muted red
)







@Composable
fun FitnessCompanionFrontTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme, // Apply your custom color scheme
        typography = Typography,  // Add typography if applicable
        content = content
    )
}
