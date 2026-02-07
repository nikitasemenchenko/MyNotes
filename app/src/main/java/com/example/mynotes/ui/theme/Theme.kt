package com.example.compose

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.mynotes.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.mynotes.ui.theme.inversePrimaryDarkHighContrast
import com.example.mynotes.ui.theme.surfaceBrightDarkHighContrast
import com.example.mynotes.ui.theme.surfaceContainerDarkHighContrast
import com.example.mynotes.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.mynotes.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.mynotes.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.mynotes.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.mynotes.ui.theme.surfaceDimDarkHighContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryLight,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryLight,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerLight,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerLight,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryLight,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryLight,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerLight,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerLight,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryLight,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryLight,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerLight,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorLight,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorLight,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerLight,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerLight,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundLight,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundLight,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceLight,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceLight,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantLight,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantLight,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineLight,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantLight,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimLight,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceLight,
    inverseOnSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseOnSurfaceLight,
    inversePrimary = _root_ide_package_.com.example.mynotes.ui.theme.inversePrimaryLight,
    surfaceDim = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDimLight,
    surfaceBright = _root_ide_package_.com.example.mynotes.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowLight,
    surfaceContainer = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryDark,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryDark,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerDark,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerDark,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryDark,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryDark,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerDark,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerDark,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryDark,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryDark,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerDark,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorDark,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorDark,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerDark,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerDark,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundDark,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundDark,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDark,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceDark,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantDark,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantDark,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineDark,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantDark,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimDark,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceDark,
    inverseOnSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseOnSurfaceDark,
    inversePrimary = _root_ide_package_.com.example.mynotes.ui.theme.inversePrimaryDark,
    surfaceDim = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDimDark,
    surfaceBright = _root_ide_package_.com.example.mynotes.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowDark,
    surfaceContainer = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryLightMediumContrast,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryLightMediumContrast,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryLightMediumContrast,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerLightMediumContrast,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorLightMediumContrast,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorLightMediumContrast,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerLightMediumContrast,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundLightMediumContrast,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundLightMediumContrast,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceLightMediumContrast,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineLightMediumContrast,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantLightMediumContrast,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimLightMediumContrast,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = _root_ide_package_.com.example.mynotes.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = _root_ide_package_.com.example.mynotes.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryLightHighContrast,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryLightHighContrast,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryLightHighContrast,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerLightHighContrast,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorLightHighContrast,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorLightHighContrast,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerLightHighContrast,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundLightHighContrast,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundLightHighContrast,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceLightHighContrast,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantLightHighContrast,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineLightHighContrast,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantLightHighContrast,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimLightHighContrast,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = _root_ide_package_.com.example.mynotes.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = _root_ide_package_.com.example.mynotes.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryDarkMediumContrast,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryDarkMediumContrast,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorDarkMediumContrast,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorDarkMediumContrast,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerDarkMediumContrast,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundDarkMediumContrast,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundDarkMediumContrast,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDarkMediumContrast,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineDarkMediumContrast,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantDarkMediumContrast,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimDarkMediumContrast,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = _root_ide_package_.com.example.mynotes.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = _root_ide_package_.com.example.mynotes.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = _root_ide_package_.com.example.mynotes.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = _root_ide_package_.com.example.mynotes.ui.theme.primaryDarkHighContrast,
    onPrimary = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = _root_ide_package_.com.example.mynotes.ui.theme.secondaryDarkHighContrast,
    onSecondary = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryDarkHighContrast,
    onTertiary = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = _root_ide_package_.com.example.mynotes.ui.theme.onTertiaryContainerDarkHighContrast,
    error = _root_ide_package_.com.example.mynotes.ui.theme.errorDarkHighContrast,
    onError = _root_ide_package_.com.example.mynotes.ui.theme.onErrorDarkHighContrast,
    errorContainer = _root_ide_package_.com.example.mynotes.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = _root_ide_package_.com.example.mynotes.ui.theme.onErrorContainerDarkHighContrast,
    background = _root_ide_package_.com.example.mynotes.ui.theme.backgroundDarkHighContrast,
    onBackground = _root_ide_package_.com.example.mynotes.ui.theme.onBackgroundDarkHighContrast,
    surface = _root_ide_package_.com.example.mynotes.ui.theme.surfaceDarkHighContrast,
    onSurface = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = _root_ide_package_.com.example.mynotes.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = _root_ide_package_.com.example.mynotes.ui.theme.outlineDarkHighContrast,
    outlineVariant = _root_ide_package_.com.example.mynotes.ui.theme.outlineVariantDarkHighContrast,
    scrim = _root_ide_package_.com.example.mynotes.ui.theme.scrimDarkHighContrast,
    inverseSurface = _root_ide_package_.com.example.mynotes.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun MyNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

