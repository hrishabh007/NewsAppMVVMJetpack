package com.app.newsappmvvmjetpack.data.datasource.remote.VideoPost

import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO

interface VideoPostDataSource {

        suspend fun getVideoPost(
            page: Int,
            count: Int
        ): GetRecentPostDTO

}