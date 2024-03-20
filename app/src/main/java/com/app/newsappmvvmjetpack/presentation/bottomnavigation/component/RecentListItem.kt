package com.app.newsappmvvmjetpack.presentation.bottomnavigation.component

import android.text.Html
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.size.Size
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.common.CommonFunction
import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost


@Composable
fun RecentListItem(recentPost: RecentPost, isLarge: Boolean, onItemClick: (RecentPost) -> Unit) =
    if (isLarge) {
        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Box(
                Modifier
                    .padding()
                    .clickable { onItemClick(recentPost) }
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
//                val painter =
//                    rememberAsyncImagePainter(model = Constants.BASE_URL + "upload" + recentPost.newsImage)
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.three),
                    model = Constants.BASE_URL + "upload/" + recentPost.newsImage,
                    contentDescription = "This is an example image"
                )
                Text(
                    recentPost.newsTitle,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 30.dp, start = 10.dp, end = 10.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                ) {

                    Image(
                        painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_low), // Replace with your desired image ID
                        contentDescription = "Settings Icon", // Provide a description for accessibility
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color.White) // Optional: Set image size
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        CommonFunction.getTimeAgo(recentPost.newsDate).toString(),
                        textAlign = TextAlign.Start,

                        color = Color.White,
                    )
                }


            }
        }

    } else {
        Card(
            modifier = Modifier.padding(top = 20.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Box(
                Modifier
                    .padding()
                    .clickable { onItemClick(recentPost) }
                    .fillMaxWidth()

            ) {
                if (recentPost.contentType == "youtube") {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                            .clickable { onItemClick(recentPost) },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 105.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                        ) {
                            val accountCircleIcon: ImageVector = Icons.Default.PlayArrow

                            AsyncImage(
                                modifier = Modifier.matchParentSize(),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(id = R.drawable.three),
                                model = Constants.YOUTUBE_IMG_FRONT + recentPost.videoId + Constants.YOUTUBE_IMG_BACK,
                                contentDescription = "This is an example image"
                            )

                            Image(

                                imageVector =  accountCircleIcon,// Replace with your desired image ID
                                contentDescription = "Settings Icon", // Provide a description for accessibility
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.align(Alignment.Center).size(50.dp),// Optional: Set image size
                            )
                        }

                        Column(modifier = Modifier.padding(top = 0.dp, start = 10.dp)) {
                            Text(
                                text = recentPost.newsTitle,
                                fontFamily = CommonFunction.poppinsBold,
                                maxLines = 2,
                                style = TextStyle(fontSize = 16.sp),
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(
                                text = Html.fromHtml(recentPost.newsDescription).toString(),
                                fontFamily = CommonFunction.poppinsSemibold,
                                maxLines = 2,
                                style = TextStyle(fontSize = 14.sp),
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                            ) {

                                Image(
                                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_low), // Replace with your desired image ID
                                    contentDescription = "Settings Icon", // Provide a description for accessibility
                                    modifier = Modifier.size(15.dp),
                                    colorFilter = ColorFilter.tint(Color.Gray)// Optional: Set image size
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    CommonFunction.getTimeAgo(recentPost.newsDate).toString(),
                                    textAlign = TextAlign.Start,
                                    color = Color.Gray,
                                    style = TextStyle(fontSize = 13.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    fontFamily = CommonFunction.poppinsRegular,
                                )
                            }
                        }


                    }
                } else {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                            .clickable { onItemClick(recentPost) },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(width = 100.dp, height = 105.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.three),
                            model = Constants.BASE_URL + "upload/" + recentPost.newsImage,
                            contentDescription = "This is an example image"
                        )
                        Column(modifier = Modifier.padding(top = 0.dp, start = 10.dp)) {
                            Text(
                                text = "${recentPost.newsTitle}",
                                fontFamily = CommonFunction.poppinsBold,
                                maxLines = 2,
                                style = TextStyle(fontSize = 16.sp),
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(
                                text = Html.fromHtml(recentPost.newsDescription).toString(),
                                fontFamily = CommonFunction.poppinsSemibold,
                                maxLines = 2,
                                style = TextStyle(fontSize = 14.sp),
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                            ) {

                                Image(
                                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_low), // Replace with your desired image ID
                                    contentDescription = "Settings Icon", // Provide a description for accessibility
                                    modifier = Modifier.size(15.dp),
                                    colorFilter = ColorFilter.tint(Color.Gray)// Optional: Set image size
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    CommonFunction.getTimeAgo(recentPost.newsDate).toString(),
                                    textAlign = TextAlign.Start,
                                    color = Color.Gray,
                                    style = TextStyle(fontSize = 13.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    fontFamily = CommonFunction.poppinsRegular,
                                )
                            }
                        }


                    }
                }

            }
        }

    }

@Preview
@Composable
fun RecentListItemPreview() {
    val recentPost = RecentPost(
        catId = 2,
        categoryName = "Technology",
        commentsCount = 0,
        contentType = "Post",
        newsDate = "2021-07-19 03:53:02",
        newsDescription = Constants.previewtext,
        newsImage = "1584201501_Android-10-akan-menjadi-nama-resmi-Android-Q.jpg",
        newsTitle = "Google deserts desserts: Android 10 is the official name for Android Q",
        nid = 24,
        videoId = "cda11up",
        videoUrl = "",
        viewCount = 127
    )
    RecentListItem(
        isLarge = false,
        onItemClick = {
            // navController.navigate(AppScreen.DetailsScreen.route)
        }, recentPost = recentPost
    )
}