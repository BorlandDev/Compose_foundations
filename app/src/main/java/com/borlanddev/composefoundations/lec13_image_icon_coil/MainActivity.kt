package com.borlanddev.composefoundations.lec13_image_icon_coil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.borlanddev.composefoundations.R
import com.borlanddev.composefoundations.lec13_image_icon_coil.base.Container

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AppScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            VectorIcon()
            TintedVectorIcon()
            MaterialIcon()
            IconWithModifier()
            SimpleImage()
            ContentScaleCropImage()
            SquareImage()
            ClippedImage()
            ClippedImageWithCustomShape()
            SimpleAsyncLoadedImage()
            AsyncLoadedImage()

            Spacer(modifier = Modifier.height(12.dp))
        }
    }

    @Composable
    fun VectorIcon() {
        Container(name = "Simple vector icon") {
            Icon(
                painter = painterResource(id = R.drawable.ic_album),
                contentDescription = "Album icon",
                modifier = Modifier.size(48.dp)
            )
        }
    }

    @Composable
    fun TintedVectorIcon() {
        Container(name = stringResource(id = R.string.tinted_vector_icon)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = stringResource(id = R.string.tinted_vector_icon),
                tint = Color.Green,
                modifier = Modifier.size(48.dp)
            )
        }
    }

    @Composable
    fun IconWithModifier() {
        Container(name = stringResource(id = R.string.vector_icon_with_modifiers)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plane),
                contentDescription = stringResource(id = R.string.vector_icon_with_modifiers),
                tint = Color.Yellow,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Blue, CircleShape)
                    .padding(8.dp)
            )
        }
    }

    @Composable
    fun MaterialIcon() {
        Container(name = stringResource(id = R.string.material_icon)) {
            Icon(
                imageVector = Icons.Rounded.MailOutline,
                modifier = Modifier.size(48.dp),
                contentDescription = stringResource(id = R.string.material_icon)
            )
        }
    }

    @Composable
    fun SimpleImage() {
        Container(name = stringResource(id = R.string.simple_image)) {
            Image(
                painter = painterResource(id = R.drawable.toyota),
                contentDescription = null,
            )
        }
    }

    @Composable
    fun ContentScaleCropImage() {
        Container(name = "Crop image") {
            Image(
                painter = painterResource(id = R.drawable.android_marshmellow),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }

    @Composable
    fun SquareImage() {
        Container(name = "Square image") {
            Image(
                painter = painterResource(id = R.drawable.black_windows),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(1f / 1f)
            )
        }
    }

    @Composable
    fun ClippedImage() {
        Container(name = "Clipped image") {
            Image(
                painter = painterResource(id = R.drawable.kotlindroid),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f / 1f)
                    .clip(CircleShape)
            )
        }
    }

    @Composable
    fun ClippedImageWithCustomShape() {
        Container(name = "Clipped Image With Custom Shape") {
            Image(
                painter = painterResource(id = R.drawable.mechanical_keyboard),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f / 1f)
                    .clip(GenericShape { size, _ ->
                        moveTo(0f, size.height)
                        lineTo(size.width / 2, 0f)
                        lineTo(size.width, size.height)
                    })
            )
        }
    }

    @Composable
    fun SimpleAsyncLoadedImage() {
        Container(name = "Simple Async Loaded Image By Coil") {
            AsyncImage(
                model = "https://unsplash.com/photos/zB-3YxCdWcA/download?ixid=M3wxMjA3fDB8MXxhbGx8Mnx8fHx8fDJ8fDE3MDcxNTAxNTF8&force=true",
                contentDescription = null
            )
        }
    }

    enum class RequestState {
        LOAD_NOT_REQUESTED,
        LOAD_REQUESTED
    }

    @Composable
    fun AsyncLoadedImage() {
        var state by remember {
            mutableStateOf(RequestState.LOAD_NOT_REQUESTED)
        }

        Container(name = "Async load by Coil with state tracking") {
            when (state) {
                RequestState.LOAD_NOT_REQUESTED -> {
                    LoadButton { state = RequestState.LOAD_REQUESTED }
                }

                RequestState.LOAD_REQUESTED -> {
                    SubcomposeAsyncImage(
                        model = "https://unsplash.com/photos/yjRE4-gAnkk/download?ixid=M3wxMjA3fDB8MXxhbGx8MzR8fHx8fHwyfHwxNzA3MTUwNjIwfA&force=true",
                        contentDescription = null,
                        loading = {
                            CircularProgressIndicator()
                        }, error = {
                            Text("Load")
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun LoadButton(
        onClick: () -> Unit
    ) {
        Button(onClick = onClick) {
            Text("Load")
        }
    }
}