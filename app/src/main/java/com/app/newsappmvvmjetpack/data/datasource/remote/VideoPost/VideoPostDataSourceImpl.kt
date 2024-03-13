package com.app.newsappmvvmjetpack.data.datasource.remote.VideoPost

import com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost.RecentPostDataSource
import com.app.newsappmvvmjetpack.data.remote.RetrofitService
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import javax.inject.Inject

class VideoPostDataSourceImpl @Inject constructor(
    private val api: RetrofitService
) : VideoPostDataSource {

    override suspend fun getVideoPost(page: Int, count: Int): GetRecentPostDTO {
        return api.getVideoPost(page = page, count = count)
    }


}