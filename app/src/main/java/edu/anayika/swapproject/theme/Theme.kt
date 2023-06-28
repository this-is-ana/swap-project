package edu.anayika.swapproject.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = Primary_Dark,
    secondary = Secondary_Dark,
    tertiary = Tertiary_Dark,
    error = Error_Dark,
    background = Background_Dark,
    outline = Outline_Dark,
    onPrimary = On_Primary_Dark,
    onSecondary = On_Secondary_Dark,
    onTertiary = On_Tertiary_Dark,
    onError = On_Error_Dark,
    onBackground = On_Background_Dark,
    onSurface = On_Surface_Dark,
    primaryContainer =  Primary_Container_Dark,
    secondaryContainer =  Secondary_Container_Dark,
    tertiaryContainer = Tertiary_Container_Dark,
    errorContainer = Error_Container_Dark,
    surface = Surface_Dark,
    surfaceVariant = Surface_Variant_Dark,
    onPrimaryContainer =  On_Primary_Container_Dark,
    onSecondaryContainer =  On_Secondary_Container_Dark,
    onTertiaryContainer = On_Tertiary_Container_Dark,
    onErrorContainer = On_Error_Container_Dark,
    onSurfaceVariant = On_Surface_Variant_Dark
)

 val LightColorScheme = lightColorScheme(
    primary = Primary_Light,
    secondary = Secondary_Light,
    tertiary = Tertiary_Light,
    error = Error_Light,
    background = Background_Light,
    outline = Outline_Light,
    onPrimary = On_Primary_Light,
    onSecondary = On_Secondary_Light,
    onTertiary = On_Tertiary_Light,
    onError = On_Error_Light,
    onBackground = On_Background_Light,
    onSurface = On_Surface_Light,
    primaryContainer =  Primary_Container_Light,
    secondaryContainer =  Secondary_Container_Light,
    tertiaryContainer = Tertiary_Container_Light,
    errorContainer = Error_Container_Light,
    surface = Surface_Light,
    surfaceVariant = Surface_Variant_Light,
    onPrimaryContainer = On_Primary_Container_Light,
    onSecondaryContainer =  On_Secondary_Container_Light,
    onTertiaryContainer = On_Tertiary_Container_Light,
    onErrorContainer = On_Error_Container_Light,
    onSurfaceVariant = On_Surface_Variant_Light
)

@JvmOverloads
@Composable
fun SwapProject_Theme(
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}