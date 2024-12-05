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
    primary = DarkBrown,
    secondary = AccentBrown,
    tertiary = LightBrown,
    background = BackgroundBrown,
    surface = SurfaceBrown,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFFBE9E7), // Light contrast for text
    onSurface = Color(0xFFFBE9E7) // Light contrast for text
)

private val LightColorScheme = lightColorScheme(
    primary = LightBrown,
    secondary = AccentBrown,
    tertiary = DarkBrown,
    background = Color(0xFFFFF8E1), // Very light background for light theme
    surface = Color(0xFFFFF8E1), // Very light surface for light theme
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFF4E342E), // Dark contrast for text
    onSurface = Color(0xFF4E342E) // Dark contrast for text
)

@Composable
fun FitnessCompanionFrontTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = PrimaryGrey,
            secondary = SecondaryGrey,
            background = BlackBackground,
            surface = DarkGreySurface,
            onPrimary = Color.Black,
            onSecondary = Color.White,
            onBackground = GreyOnSurface,
            onSurface = GreyOnSurface,
            error = ErrorRed,
            onError = Color.White
        ),
        typography = Typography,  // You can define typography here if needed
        content = content
    )

}