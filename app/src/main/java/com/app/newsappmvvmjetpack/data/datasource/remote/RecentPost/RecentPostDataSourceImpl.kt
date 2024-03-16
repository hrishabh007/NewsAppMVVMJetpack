package com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost

import com.app.newsappmvvmjetpack.data.remote.RetrofitService
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import javax.inject.Inject

class RecentPostDataSourceImpl @Inject constructor(
    private val api: RetrofitService
) : RecentPostDataSource {
    override suspend fun getRecentPost(page: Int, count: Int): GetRecentPostDTO {
        return api.getRecentPost(page = page, count = count)
    }

}