package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Preview(showSystemUi = true)
@Composable
fun Example02Center() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Square(
            color = Color.Red,
            modifier = Modifier.constrainAs(createRef()) {
                centerVerticallyTo(parent)
            }
        )
        Square(
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()) {
                centerHorizontallyTo(parent)
            }
        )
    }
}