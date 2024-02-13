package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Preview(showSystemUi = true, locale = "ar")
@Composable
fun Example04AbsoluteLinksToParent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Square(
            color = Color.Red,
            modifier = Modifier
                .constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Square(
            color = Color.Red,
            modifier = Modifier
                .constrainAs(createRef()) {
                    absoluteLeft.linkTo(parent.absoluteLeft)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}