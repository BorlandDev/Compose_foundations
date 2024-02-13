package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Preview(showSystemUi = true)
@Composable
fun Example01Center() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Square(
            modifier = Modifier.constrainAs(createRef()) {
                centerTo(parent)
            }
        )
    }
}