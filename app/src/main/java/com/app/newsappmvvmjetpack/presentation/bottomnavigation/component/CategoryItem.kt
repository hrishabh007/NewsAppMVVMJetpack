package com.app.newsappmvvmjetpack.presentation.bottomnavigation.component

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import coil.compose.AsyncImage
import com.app.newsappmvvmjetpack.common.CommonFunction
import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.Category
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost


@Composable
fun CategoryItem(category: Category, onItemClick: (Category) -> Unit) {

    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .size(width = 100.dp, height = 105.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = com.app.newsappmvvmjetpack.R.drawable.three),
            model = Constants.BASE_URL + "upload/category/" + category.categoryImage,
            contentDescription = "This is an example image"
        )
        Text(
            text = category.categoryName,
            fontFamily = CommonFunction.poppinsBold,
            maxLines = 1,
            style = TextStyle(fontSize = 16.sp),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "(${category.postCount})",
            fontFamily = CommonFunction.poppinsBold,
            maxLines = 1,
            style = TextStyle(fontSize = 16.sp),
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview
@Composable
fun CategoryItemItemPreview() {

    val category = Category(
        cid = 7,
        categoryName = "World",
        categoryImage = "0731-2018-03-28.png",
        postCount = 5

    )
    CategoryItem(
        category = category, onItemClick = {}
    )
}