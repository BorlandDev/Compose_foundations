package com.borlanddev.composefoundations.lec18_composition_local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.borlanddev.composefoundations.lec18_composition_local.controller.AppThemeController
import com.borlanddev.composefoundations.lec18_composition_local.controller.EmptyThemeController
import com.borlanddev.composefoundations.lec18_composition_local.controller.RealThemeController
import com.borlanddev.composefoundations.lec18_composition_local.data.AppTheme
import com.borlanddev.composefoundations.lec18_composition_local.data.SharedPreferencesThemeDataSource

/**
 * Provides the current app theme.
 * @see [AppTheme]
 */
val LocalAppTheme = compositionLocalOf<AppTheme> {
    AppTheme.Light
}


/**
 * Provides the current app theme controller witch can toggle an app theme.
 * @see [AppThemeController]
 */
val LocalThemeController = staticCompositionLocalOf<AppThemeController> {
    EmptyThemeController
}

/**
 * Base container for components witch render itself by using app theme
 * provided by [LocalAppTheme] provide
 */
@Composable
fun AppThemeContainer(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val themeDataSource = remember {
        SharedPreferencesThemeDataSource(context)
    }
    val controller = remember {
        RealThemeController(themeDataSource)
    }
    val theme by themeDataSource.themeStateFlow.collectAsStateWithLifecycle()
    CompositionLocalProvider(
        LocalAppTheme provides theme,
        LocalThemeController provides controller
    ) {
        content()
    }
}