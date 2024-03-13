package com.app.newsappmvvmjetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost.RecentPostDataSource
import com.app.newsappmvvmjetpack.data.datasource.remote.VideoPost.VideoPostDataSource
import com.app.newsappmvvmjetpack.data.remote.RetrofitService
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.GetCategoryDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.GetSettingsDTO
import com.app.newsappmvvmjetpack.data.repository.paging.RecentPostPagingSource
import com.app.newsappmvvmjetpack.data.repository.paging.VideoPostPagingSource
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val recentRemoteDataSource: RecentPostDataSource,
    private val videoRemoteDataSource:VideoPostDataSource
) : NewsRepository {
    override suspend fun getSettings(): GetSettingsDTO {
        return retrofitService.getSettings()
    }

    override suspend fun getCategories(): GetCategoryDTO {
        return retrofitService.getCategory()
    }

    override suspend fun getRecentPost(): Flow<PagingData<RecentPost>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                RecentPostPagingSource(recentRemoteDataSource)
            }
        ).flow
    }

    override suspend fun getVideoPost(): Flow<PagingData<RecentPost>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                VideoPostPagingSource(videoRemoteDataSource)
            }
        ).flow
    }

//    override suspend fun getRecentPost(page: Int, count: Int): Flow<PagingData<RecentPost>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
//            pagingSourceFactory = {
//                RecentPostPagingSource(remoteDataSource)
//            }
//        ).flow
//    }

//    override suspend fun getRecentPost(page: Int, count: Int): Flow<PagingData<GetRecentPost>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
//            pagingSourceFactory = {
//                RecentPostPagingSource(remoteDataSource)
//            }
//        ).flow
//    }

//    override suspend fun getRecentPost(page: Int, count: Int): GetRecentPostDTO {
//        return retrofitService.getRecentPost(page = page, count = count)
//    }

}