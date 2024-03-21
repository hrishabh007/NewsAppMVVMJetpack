package com.app.newsappmvvmjetpack.presentation.newsDetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.Image
import kotlin.math.roundToInt

@Composable
fun ImageSlider(imageList: List<Image>) {


    FullWidthImageSlider(imageList = imageList)
}

@Composable
fun FullWidthImageSlider(
    imageList: List<Image>,
) {
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.height(300.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(imageList.size) { index ->
                ImageItem(
                    resId = imageList[index],
                    onClick = { currentIndex = index },
                    isSelected = index == currentIndex
                )

            }
        }
        DotIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 8.dp),
            currentIndex = currentIndex,
            count = imageList.size
        )
    }

}

@Composable
fun ImageItem(
    resId: Image,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Surface(
        color = if (isSelected) Color.Gray else Color.Transparent,
        modifier = Modifier
            .fillMaxHeight()
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.three),
            model = Constants.BASE_URL + "upload/" + resId.imageName,
            contentDescription = "This is an example image"
        )
    }
}


@Composable
fun DotIndicator(modifier: Modifier = Modifier, currentIndex: Int, count: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(count) { index ->
            Dot(isSelected = index == currentIndex)
        }
    }
}

@Composable
fun Dot(isSelected: Boolean) {
    val color = if (isSelected) Color.Black else Color.Gray
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, shape = CircleShape)
    )
}

