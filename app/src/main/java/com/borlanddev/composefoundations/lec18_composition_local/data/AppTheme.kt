package com.borlanddev.composefoundations.lec18_composition_local.data

import androidx.compose.ui.graphics.Color

/**
 * Predefined colors for app widgets placed into [AppThemeContainer]
 */

data class AppTheme(
    val buttonColor: Color,
    val bgColor: Color
) {
    companion object {
        val Light = AppTheme(
            buttonColor = Color.Blue,
            bgColor = Color.White
        )

        val Dark = AppTheme(
            buttonColor = Color.Gray,
            bgColor = Color.Black
        )
    }
}
