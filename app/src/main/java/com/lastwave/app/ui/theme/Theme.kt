package com.lastwave.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.core.view.WindowCompat
import com.lastwave.app.data.local.AccentMode
import com.lastwave.app.data.repository.ThemeUiState

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.staticCompositionLocalOf
import com.lastwave.app.data.local.ThemeMode

/** Shared composition local indicating whether the active theme is dark mode. */
val LocalIsDarkTheme = staticCompositionLocalOf { true }
/** Shared composition local indicating whether bottom navigation tabs are hidden. */
val LocalHideNavTabs = staticCompositionLocalOf { false }

/**
 * Wraps the whole app. Supports System Default, Light, and Dark modes.
 * Kyant0 Backdrop captures underlying content into a hardware-accelerated layer
 * shared app-wide; screens add their own sibling sources for local content
 * (nav bar, player, headers).
 */
@Composable
fun LastWaveTheme(
    themeState: ThemeUiState,
    content: @Composable () -> Unit,
) {
    // HMusic: siempre oscuro, como YouTube Music.
    val isDark = true
    // Dynamic Color ON (S+): use the live wallpaper Monet scheme instead of the
    // default manual accent or monochrome. System dynamic auto-tracks wallpaper
    // changes, so no manual refresh is needed. Now-playing artwork wins when it
    // is active; pre-S falls back to the repository's wallpaper-seeded scheme.
    val context = LocalContext.current
    val useSystemDynamic = themeState.mode == AccentMode.DYNAMIC &&
        !themeState.isNowPlayingThemed &&
        !themeState.isLastWaveTheme &&
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val systemDark = if (useSystemDynamic) {
        val base = dynamicDarkColorScheme(context)
        if (themeState.amoled) {
            base.copy(
                background = Color.Black,
                surface = Color.Black,
                surfaceContainerLow = Color.Black,
                surfaceContainerLowest = Color.Black,
            )
        } else base
    } else null
    val systemLight = if (useSystemDynamic) dynamicLightColorScheme(context) else null
    val activeColorScheme = when {
        themeState.isLastWaveTheme && isDark -> themeState.darkColorScheme
        themeState.isLastWaveTheme && !isDark -> themeState.lightColorScheme
        systemDark != null && isDark -> systemDark
        systemLight != null && !isDark -> systemLight
        isDark -> themeState.darkColorScheme
        else -> themeState.lightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            runCatching {
                window.statusBarColor = android.graphics.Color.TRANSPARENT
                window.navigationBarColor = android.graphics.Color.TRANSPARENT
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDark
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    window.isStatusBarContrastEnforced = false
                    window.isNavigationBarContrastEnforced = false
                }
            }
        }
    }

    // Pantallas pequeñas / "tamaño de pantalla" o "tamaño de fuente" grandes:
    // en teléfonos donde el ancho útil baja de 360dp (o la fuente del sistema
    // es muy grande) los botones fijos se cortaban o se salían de su lugar.
    // Escalamos la densidad para que el ancho efectivo nunca sea menor a
    // 360dp y limitamos el escalado de fuente a 1.15x. Así todo el diseño
    // conserva sus proporciones en cualquier celular.
    val baseDensity = LocalDensity.current
    val configuration = LocalConfiguration.current
    val adjustedDensity = remember(baseDensity, configuration.screenWidthDp) {
        adaptiveDensity(baseDensity, configuration.screenWidthDp)
    }

    CompositionLocalProvider(LocalDensity provides adjustedDensity) {
        MaterialTheme(
            // Paleta fija estilo YouTube Music (negro, blanco y rojo).
            colorScheme = HMusicColorScheme,
            typography = if (themeState.useCustomFont) LastWaveTypography else SystemTypography,
            shapes = LastWaveShapes,
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                CompositionLocalProvider(
                    // HMusic: sin vidrio líquido ni dock flotante; barra plana como YouTube Music.
                    LocalLiquidGlass provides false,
                    LocalMd3NavBar provides false,
                    LocalHideNavTabs provides themeState.hideNavTabs,
                    LocalIsDarkTheme provides isDark,
                ) {
                    content()
                }
            }
        }
    }
}

/** Ancho mínimo (dp) para el que está diseñada la interfaz. */
private const val MIN_DESIGN_WIDTH_DP = 360f

/** Escalado máximo de fuente que la interfaz soporta sin cortar botones. */
private const val MAX_FONT_SCALE = 1.15f

/**
 * Devuelve una densidad corregida: si el ancho de pantalla es menor que
 * [MIN_DESIGN_WIDTH_DP] se reduce la densidad proporcionalmente (el diseño
 * se "encoge" un poco en vez de cortarse) y el escalado de fuente se
 * limita a [MAX_FONT_SCALE].
 */
internal fun adaptiveDensity(base: Density, screenWidthDp: Int): Density {
    val widthDp = screenWidthDp.toFloat()
    val densityFactor = if (widthDp > 0f && widthDp < MIN_DESIGN_WIDTH_DP) {
        widthDp / MIN_DESIGN_WIDTH_DP
    } else {
        1f
    }
    val fontScale = base.fontScale.coerceIn(0.85f, MAX_FONT_SCALE)
    if (densityFactor == 1f && fontScale == base.fontScale) return base
    return Density(density = base.density * densityFactor, fontScale = fontScale)
}


/** Paleta HMusic inspirada en YouTube Music: fondo casi negro, texto blanco, rojo de acento. */
val HMusicColorScheme = androidx.compose.material3.darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF0F0F0F),
    primaryContainer = Color(0xFF272727),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFAAAAAA),
    onSecondary = Color(0xFF0F0F0F),
    secondaryContainer = Color(0xFF272727),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFFFF0033),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF3A1016),
    onTertiaryContainer = Color(0xFFFFDADB),
    error = Color(0xFFFF4E45),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFF3B1412),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF030303),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF030303),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF212121),
    onSurfaceVariant = Color(0xFFAAAAAA),
    surfaceTint = Color(0xFF030303),
    outline = Color(0xFF3F3F3F),
    outlineVariant = Color(0xFF2A2A2A),
    inverseSurface = Color(0xFFFFFFFF),
    inverseOnSurface = Color(0xFF0F0F0F),
    inversePrimary = Color(0xFF0F0F0F),
    surfaceBright = Color(0xFF2A2A2A),
    surfaceDim = Color(0xFF030303),
    surfaceContainerLowest = Color(0xFF000000),
    surfaceContainerLow = Color(0xFF0F0F0F),
    surfaceContainer = Color(0xFF0F0F0F),
    surfaceContainerHigh = Color(0xFF1D1D1D),
    surfaceContainerHighest = Color(0xFF272727),
)
