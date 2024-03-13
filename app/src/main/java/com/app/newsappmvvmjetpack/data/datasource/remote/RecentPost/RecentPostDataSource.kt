package com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost

import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO

interface RecentPostDataSource {
    suspend fun getRecentPost(
        page: Int,
        count: Int
    ): GetRecentPostDTO
}