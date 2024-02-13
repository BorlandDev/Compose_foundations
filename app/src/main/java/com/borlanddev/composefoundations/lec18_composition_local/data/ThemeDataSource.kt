package com.borlanddev.composefoundations.lec18_composition_local.data

import kotlinx.coroutines.flow.StateFlow

/**
 * Store for the app theme. It hold the current app theme and allows changing if it needed.
 */

interface ThemeDataSource {
    /**
     * Observe the current app theme.
     * This flow emits a theme every time after updating it by [setTheme] call.
     */

    val themeStateFlow: StateFlow<AppTheme>

    /**
     * Update the current app theme. This call triggers updating of [themeStateFlow]
     * if you pass the [theme] witch differs from the current theme.
     */
    fun setTheme(theme: AppTheme)
}