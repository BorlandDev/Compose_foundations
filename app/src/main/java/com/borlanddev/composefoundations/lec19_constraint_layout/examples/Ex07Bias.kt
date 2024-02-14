package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Rectangle
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Composable
@Preview(showSystemUi = true)
fun Example07Bias() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            startBound,
            endBound
        ) = createRefs()

        // start (left) bound
        Rectangle(
            width = 10.dp,
            height = 300.dp,
            color = Color.Black,
            modifier = Modifier.constrainAs(startBound) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start, margin = 40.dp)
            }
        )

        Rectangle(
            width = 10.dp,
            height = 300.dp,
            color = Color.Black,
            modifier = Modifier.constrainAs(endBound) {
                centerVerticallyTo(parent)
                end.linkTo(parent.end, margin = 40.dp)
            }
        )

        Square(
            size = 30.dp,
            modifier = Modifier.constrainAs(createRef()) {
                linkTo(
                    start = startBound.end,
                    end = endBound.start,
                    bias = 0.25f
                )
                linkTo(
                    top = startBound.top,
                    bottom = startBound.bottom,
                    bias = 0.75f
                )
            }
        )

    }
}