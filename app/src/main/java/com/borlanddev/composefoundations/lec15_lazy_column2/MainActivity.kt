package com.borlanddev.composefoundations.lec15_lazy_column2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.borlanddev.composefoundations.R
import com.github.javafaker.Faker
import java.util.Random

data class User(
    val id: Long,
    val photoUrl: String,
    val name: String,
    val status: String
)

fun createUserList(): List<User> {
    val faker = Faker.instance(Random(0))
    val images = mutableListOf(
        "https://unsplash.com/photos/rDEOVtE7vOs/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8Mnx8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/c_GmwfHBDzk/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8M3x8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/QXevDflbl8A/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8NHx8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/jmURdhtm7Ng/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8N3x8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/pg_WCHWSdT8/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MTF8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM1NDh8MA&force=true",
        "https://unsplash.com/photos/t3zrEm88ehc/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MTh8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM1NDh8MA&force=true",
        "https://unsplash.com/photos/IF9TK5Uy-KI/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MjN8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/jgSAuqMmJUE/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8Mjh8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/15Vb4B_ma_s/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MzZ8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/hh3ViD0r0Rc/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8NDN8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2NDJ8MA&force=true"
    )
    val list = List(100) { index ->
        val id = index + 1L
        User(
            id = id,
            photoUrl = images[index % images.size],
            name = faker.name().fullName(),
            status = faker.shakespeare().hamletQuote()
        )
    }
    return list
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun AppScreen() {
        var userList by remember {
            mutableStateOf(createUserList())
        }
        val context = LocalContext.current
        LazyColumn(contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            items(
                items = userList,
                key = { user -> user.id }) { user ->
                UserCard(
                    user = user,
                    onUserClicked = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.clicked_on, user.name),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onUserDeleted = {
                        userList -= user
                    },
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    @Composable
    fun UserCard(
        user: User,
        onUserClicked: () -> Unit,
        onUserDeleted: () -> Unit,
        modifier: Modifier
    ) {
        Card(
            shape = RoundedCornerShape(size = 6.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    onUserClicked()
                }
        ) {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                UserImage(url = user.photoUrl)
                Spacer(modifier = Modifier.width(16.dp))
                UserInfo(user = user)
                DeleteUserButton(onClick = onUserDeleted)
            }
        }
    }

    @Composable
    private fun UserImage(
        url: String
    ) {
        val placeholder = rememberVectorPainter(
            image = Icons.Rounded.AccountCircle
        )
        AsyncImage(
            model = url,
            placeholder = placeholder,
            contentDescription = getString(R.string.user_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
        )
    }

    @Composable
    private fun RowScope.UserInfo(
        user: User
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = user.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = user.status,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Composable
    private fun RowScope.DeleteUserButton(
        //context: Context,
        onClick: () -> Unit
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = getString(R.string.delete_user)
            )
        }
    }
}