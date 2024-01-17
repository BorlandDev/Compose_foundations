package com.borlanddev.composefoundations.lecture10

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class ButtonState(
    val buttonColor: Int = 256434,
    val pressCount: Int = 0
) : Parcelable

@Composable
fun ButtonsExample() {
    var buttonState by rememberSaveable {
        mutableStateOf(ButtonState())
    }
    Button(
        onClick = {
            val newButtonColor = -Random.nextInt(256443)
            buttonState = buttonState.copy(
                buttonColor = newButtonColor,
                pressCount = buttonState.pressCount + 1
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(buttonState.buttonColor)
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Click me", color = Color.Black)
    }
    Text(text = "Count of clicks: ${buttonState.pressCount}")
}

@Preview(showSystemUi = true)
@Composable
private fun ButtonsPreview() {
    ButtonsExample()
}