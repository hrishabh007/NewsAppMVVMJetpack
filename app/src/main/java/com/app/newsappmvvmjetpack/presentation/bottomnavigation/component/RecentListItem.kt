package com.app.newsappmvvmjetpack.presentation.bottomnavigation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost


@Composable
fun RecentListItem(recentPost: RecentPost, onItemClick: (RecentPost) -> Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(recentPost) }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "${recentPost.categoryName}. ${recentPost.newsTitle} (${recentPost.viewCount})",
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis
        )


    }
}