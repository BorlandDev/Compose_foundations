package com.borlanddev.composefoundations.lec19_constraint_layout.examples


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.borlanddev.composefoundations.lec19_constraint_layout.Square

@Preview(showSystemUi = true)
@Composable
fun Example07LinksIntro() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val largeSquare = createRef()
        val (
            innerLeftSquare,
            innerRightSquare,
            innerTopSquare,
            innerBottomSquare
        ) = createRefs()
        val (
            outerLeftSquare,
            outerRightSquare,
            outerTopSquare,
            outerBottomSquare,
        ) = createRefs()

        Square(
            size = 200.dp,
            modifier = Modifier
                .constrainAs(largeSquare) {
                    centerTo(parent)
                }
        )

        // inner squares

        Square(
            color = Color.Blue,
            size = 50.dp,
            modifier = Modifier.constrainAs(innerLeftSquare) {
                centerVerticallyTo(largeSquare)
                start.linkTo(largeSquare.start)
            }
        )

        Square(
            color = Color.Blue,
            size = 50.dp,
            modifier = Modifier.constrainAs(innerTopSquare) {
                centerHorizontallyTo(largeSquare)
                top.linkTo(largeSquare.top)
            }
        )

        Square(
            color = Color.Blue,
            size = 50.dp,
            modifier = Modifier.constrainAs(innerRightSquare) {
                centerVerticallyTo(largeSquare)
                end.linkTo(largeSquare.end)
            }
        )

        Square(
            color = Color.Blue,
            size = 50.dp,
            modifier = Modifier.constrainAs(innerBottomSquare) {
                centerHorizontallyTo(largeSquare)
                bottom.linkTo(largeSquare.bottom)
            }
        )

        // outer squares

        Square(
            color = Color.Green,
            size = 75.dp,
            modifier = Modifier.constrainAs(outerLeftSquare) {
                centerVerticallyTo(largeSquare)
                end.linkTo(largeSquare.start)
            }
        )

        Square(
            color = Color.Green,
            size = 75.dp,
            modifier = Modifier.constrainAs(outerTopSquare) {
                centerHorizontallyTo(largeSquare)
                bottom.linkTo(largeSquare.top)
            }
        )

        Square(
            color = Color.Green,
            size = 75.dp,
            modifier = Modifier.constrainAs(outerRightSquare) {
                centerVerticallyTo(largeSquare)
                start.linkTo(largeSquare.end)
            }
        )

        Square(
            color = Color.Green,
            size = 75.dp,
            modifier = Modifier.constrainAs(outerBottomSquare) {
                centerHorizontallyTo(largeSquare)
                top.linkTo(largeSquare.bottom)
            }
        )
    }
}