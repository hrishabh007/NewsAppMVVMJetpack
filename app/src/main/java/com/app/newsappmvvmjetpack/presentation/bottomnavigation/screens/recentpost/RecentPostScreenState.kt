package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost

import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost

data class RecentPostScreenState(
    val isLoading: Boolean = false,
    val coins: PagingData<RecentPost>?=null,
    val error: String = ""
)