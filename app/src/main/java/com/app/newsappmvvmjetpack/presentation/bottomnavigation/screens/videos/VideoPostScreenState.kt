package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.videos

import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost

data class VideoPostScreenState(
    val isLoading: Boolean = false,
    val coins: PagingData<RecentPost>? = null,
    val error: String = ""
)