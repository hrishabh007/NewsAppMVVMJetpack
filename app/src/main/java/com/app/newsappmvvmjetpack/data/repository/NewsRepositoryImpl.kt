package com.app.newsappmvvmjetpack.data.repository

import com.app.newsappmvvmjetpack.data.remote.RetrofitService
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.GetSettingsDTO
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : NewsRepository {
    override suspend fun getSettings(): GetSettingsDTO {
        return retrofitService.getSettings()
    }

    override suspend fun getRecentPost(page: Int, count: Int): GetRecentPostDTO {
        return retrofitService.getRecentPost(page = page, count = count)
    }

}