package com.borlanddev.composefoundations.lec10_buttons_textfields_checkbox

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldExample() {
    var textValue by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textValue,
        onValueChange = { updatedText ->
            textValue = updatedText
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = textValue.ifBlank { "[empty]" })
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldPreview() {
    TextFieldExample()
}