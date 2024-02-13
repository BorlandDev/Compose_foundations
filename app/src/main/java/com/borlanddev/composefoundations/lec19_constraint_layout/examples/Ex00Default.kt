package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Preview(showSystemUi = true)
@Composable
fun Example00Default() {
    ConstraintLayout {
        Square(
            color = Color.Red,
            size = 200.dp
        )
        Square(
            color = Color.Blue,
            size = 150.dp
        )
        Square(
            color = Color.Green,
            size = 100.dp
        )
    }
}