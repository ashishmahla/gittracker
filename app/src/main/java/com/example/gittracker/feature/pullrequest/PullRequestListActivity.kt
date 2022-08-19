package com.example.gittracker.feature.pullrequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gittracker.ui.theme.GitTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PullRequestListActivity : ComponentActivity() {
    private val viewModel: PullRequestListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GitTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val data = viewModel.closedPullRequestList
                        .collectAsState()
                        .value
                        ?: return@Surface

                    LazyColumn {
                        items(data) {
                            UiPullRequest(it)
                        }
                    }
                }
            }
        }
    }
}

val uiDateFormat = SimpleDateFormat("dd MMM, yyyy HH:mm")

@Composable
fun UiPullRequest(
    pullRequest: PullRequestData,
) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 20.dp)
        ) {
            Row {
                val painter = rememberAsyncImagePainter(model = pullRequest.userImageUrl)

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(50))
                )

                val string = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xffeeeeee))) {
                        append(pullRequest.username)
                    }

                    withStyle(SpanStyle(color = Color(0xff999999))) {
                        append(" • ")
                        append(uiDateFormat.format(pullRequest.createdAt))
                    }
                }

                Text(
                    text = string,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                        .wrapContentHeight(),
                )
            }

            Text(
                text = pullRequest.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Closed",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4F90FF),
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x443737CE))
                        .padding(4.dp)
                        .wrapContentSize(),
                )

                Text(
                    text = "  •  " + uiDateFormat.format(pullRequest.closedAt),
                    color = Color(0xff999999),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitTrackerTheme {
        UiPullRequest(
            PullRequestData(
                "added firebase authentication, dark theme and compose",
                Date(),
                Date(),
                "ashishmahla",
                "https://yt3.ggpht.com/LVHPcedzJDEywYg5Xl2e7JJucjYjeQrhzWX7jaBGZJbKI3pofC-5Ab4TEmVUhwKAWNlEa6lQ=s88-c-k-c0x00ffffff-no-rj-mo"
            )
        )
    }
}
