package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.borlanddev.composefoundations.lec19_constraint_layout.Rectangle

@Composable
@Preview(showSystemUi = true)
fun Example12DecoupledApi() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
        constraintSet = constrains()
    ) {


        Text(
            text = "Hello world 123",
            color = Color.White,
            modifier = Modifier
                .layoutId("text1")
                .background(Color.Red)
                .padding(6.dp)
        )

        Spacer(modifier = Modifier.layoutId("spacer"))

        Text(
            text = "Lorem ipsum dolor sit amet",
            color = Color.White,
            modifier = Modifier
                .layoutId("text2")
                .background(Color.Red)
                .padding(6.dp)
        )

        Rectangle(
            width = 10.dp,
            color = Color.Black,
            modifier = Modifier.layoutId("rectangle")
        )
    }
}

private fun constrains() = ConstraintSet {
    val text1 = createRefFor("text1")
    val text2 = createRefFor("text2")
    val spacer = createRefFor("spacer")
    val rectangle = createRefFor("rectangle")

    constrain(text1) {
        centerHorizontallyTo(parent)
    }

    constrain(text2) {
        centerHorizontallyTo(parent)
    }

    constrain(spacer) {
        height = Dimension.value(10.dp)
    }

    createVerticalChain(
        text1, spacer, text2,
        chainStyle = ChainStyle.Packed
    )

    val barrier = createEndBarrier(text1, text2)

    constrain(rectangle) {
        start.linkTo(barrier)
        centerVerticallyTo(parent)
    }

}

