package com.app.newsappmvvmjetpack.domain.repository

import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.GetCategoryDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.GetNewsDetailDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.GetSettingsDTO
import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun getSettings(): GetSettingsDTO

    suspend fun getCategories(): GetCategoryDTO
    suspend fun getRecentPost(): Flow<PagingData<RecentPost>>

    suspend fun getVideoPost(): Flow<PagingData<RecentPost>>


    suspend fun getNewsDetail(id: Int): GetNewsDetailDTO

}