package com.borlanddev.composefoundations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun HelloWorld() {
        Column(
            horizontalAlignment = BiasAlignment.Horizontal(-0.8f),
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxSize()
        ) {
            Text(
                text = "Hello World!",
                fontSize = 25.sp,
                color = Color.Red,
                modifier = Modifier
                    .background(Color.Yellow)
                    .align(BiasAlignment.Horizontal(0.2f))
                    .weight(2f)
            )

            Text(
                text = "Gendalf",
                fontSize = 20.sp,
                color = Color.Blue,
                modifier = Modifier
                    .background(Color.Green)
                    .weight(4f)
            )

            Text(
                text = "43",
                fontSize = 50.sp,
                color = Color.Green,
                modifier = Modifier
                    .background(Color.Red)
                    .weight(2f)
            )
        }
    }
}
