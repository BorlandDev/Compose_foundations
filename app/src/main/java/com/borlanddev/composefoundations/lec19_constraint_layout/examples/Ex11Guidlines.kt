package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.borlanddev.composefoundations.lec19_constraint_layout.Rectangle

@Composable
@Preview(showSystemUi = true)
fun Example11Guidelines() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val startGuideline = createGuidelineFromStart(32.dp) // or 0.3f
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(0.05f) // or 32.dp
        val bottomGuideline = createGuidelineFromBottom(0.05f)

        Rectangle(
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(bottomGuideline)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
    }
}