package com.borlanddev.composefoundations.lec18_composition_local.controller

import com.borlanddev.composefoundations.lec18_composition_local.data.AppTheme
import com.borlanddev.composefoundations.lec18_composition_local.data.ThemeDataSource

class RealThemeController(
    private val themeDataSource: ThemeDataSource
) : AppThemeController {
    override fun toggle() {
        val currentTheme = themeDataSource.themeStateFlow.value
        if (currentTheme == AppTheme.Dark) {
            themeDataSource.setTheme(AppTheme.Light)
        } else {
            themeDataSource.setTheme(AppTheme.Dark)
        }
    }
}