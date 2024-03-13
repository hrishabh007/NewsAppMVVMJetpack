package com.app.newsappmvvmjetpack.domain.repository

import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.GetSettingsDTO


interface NewsRepository {
    suspend fun getSettings(): GetSettingsDTO
    suspend fun getRecentPost(page: Int, count: Int): GetRecentPostDTO
}