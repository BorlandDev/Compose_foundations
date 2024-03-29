package com.borlanddev.composefoundations.lec18_composition_local

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.borlanddev.composefoundations.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun AppScreen() {
        AppThemeContainer {
            val theme = LocalAppTheme.current
            val themeController = LocalThemeController.current
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(theme.bgColor)
            ) {
                CustomButton(
                    onClick = {
                        themeController.toggle()
                    },
                    text = stringResource(R.string.change_app_theme)
                )
            }
        }
    }

    @Composable
    fun CustomButton(
        onClick: () -> Unit,
        text: String,
        modifier: Modifier = Modifier
    ) {
        val theme = LocalAppTheme.current
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = theme.buttonColor
            )
        ) {
            Text(text = text)
        }
    }
}