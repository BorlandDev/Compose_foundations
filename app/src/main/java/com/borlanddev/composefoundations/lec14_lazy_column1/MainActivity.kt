package com.borlanddev.composefoundations.lec14_lazy_column1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Preview(showSystemUi = true)
    @Composable
    fun AppScreen() {
        val list = remember {
            List(100) { index -> "Simple text item# ${index + 1}" }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
//            item {
//                Text(
//                    text = "Header",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    modifier = Modifier.padding(all = 16.dp)
//                )
//            }

            stickyHeader {
                Text(
                    text = "Test Header 111",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(all = 16.dp)
                )
            }

            items(list) { item ->
                ListItem(text = item)
            }

            listHeader(text = "Test Header 322")


            items(30) { index ->
                ListItem(text = "Qwerty ${index + 1}")
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun LazyListScope.listHeader(text: String) {
        stickyHeader {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(all = 16.dp)
            )
        }

    }

    @Composable
    fun ListItem(text: String) {
        Text(
            text = text,
            modifier = Modifier.padding(all = 16.dp)
        )
    }
}

