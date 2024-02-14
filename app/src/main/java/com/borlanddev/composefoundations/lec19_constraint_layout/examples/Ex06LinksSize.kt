package com.borlanddev.composefoundations.lec19_constraint_layout.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.borlanddev.composefoundations.lec19_constraint_layout.Hint
import com.borlanddev.composefoundations.lec19_constraint_layout.Rectangle

@Composable
@Preview(showSystemUi = true)
fun Example06LinksSize() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            startBound,
            endBound,
            rectFillToConstraints,
            rectMatchParent,
            rectPercentage,
            rectWrapContent,
            rectPreferredWrapContent,
            rectPreferredValue,
            rectAspectRatio
        ) = createRefs()

        // --- bounds

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

        // --- squares

        // Fill to constraints
        Hint(id = rectFillToConstraints, text = "Fill to constraints")
        Rectangle(
            modifier = Modifier.constrainAs(rectFillToConstraints)
            {
                start.linkTo(startBound.start)
                end.linkTo(endBound.end)
                top.linkTo(startBound.top, margin = 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.value(10.dp)
            })

        // Match parent
        Hint(id = rectMatchParent, text = "Match parent")
        Rectangle(modifier = Modifier.constrainAs(rectMatchParent) {
            start.linkTo(startBound.start)
            end.linkTo(endBound.end)
            top.linkTo(rectFillToConstraints.bottom, margin = 30.dp)
            width = Dimension.matchParent
            height = Dimension.value(20.dp)
        })

        // Percentage
        Hint(id = rectPercentage, text = "Percentage")
        Rectangle(modifier = Modifier.constrainAs(rectPercentage) {
            start.linkTo(startBound.start)
            end.linkTo(endBound.end)
            top.linkTo(rectMatchParent.bottom, margin = 30.dp)
            width = Dimension.percent(0.7f)
            height = Dimension.value(20.dp)
        })

        // Wrap content
        Hint(id = rectWrapContent, text = "Wrap content")
        Text(
            text = "123 wef wefwgf ytnyt ykuuykuku saaasas",
            color = Color.White,
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 6.dp)
                .constrainAs(rectWrapContent) {
                    start.linkTo(startBound.start)
                    end.linkTo(endBound.end)
                    top.linkTo(rectPercentage.bottom, margin = 30.dp)
                    width = Dimension.wrapContent
                })

        // Preferred wrap content
        Hint(id = rectPreferredWrapContent, text = "Preferred wrap content")
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing fdw grergre",
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 6.dp)
                .constrainAs(rectPreferredWrapContent) {
                    start.linkTo(startBound.start)
                    end.linkTo(endBound.end)
                    top.linkTo(rectWrapContent.bottom, margin = 30.dp)
                    width = Dimension.preferredWrapContent
                })

        // Preferred value
        Hint(id = rectPreferredValue, text = "Preferred value")
        Rectangle(modifier = Modifier.constrainAs(rectPreferredValue) {
            start.linkTo(startBound.start)
            end.linkTo(endBound.end)
            top.linkTo(rectPreferredWrapContent.bottom, margin = 30.dp)
            width = Dimension.preferredValue(200.dp)
            height = Dimension.value(20.dp)
        })

        // Aspect ratio
        Hint(id = rectAspectRatio, text = "Aspect ratio")
        Rectangle(modifier = Modifier.constrainAs(rectAspectRatio) {
            start.linkTo(startBound.start)
            end.linkTo(endBound.end)
            top.linkTo(rectPreferredValue.bottom, margin = 30.dp)
            width = Dimension.percent(0.25f)
            height = Dimension.ratio("1:1")
        })
    }
}