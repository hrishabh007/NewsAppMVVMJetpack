package com.app.newsappmvvmjetpack.domain.usecase.get_video_post

import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import com.app.newsappmvvmjetpack.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoPostUseCase @Inject constructor(
    private val repository: NewsRepository
) : BaseUseCase<Unit, Flow<PagingData<RecentPost>>> {

    override suspend fun execute(input: Unit): Flow<PagingData<RecentPost>> {
        //  delay(3000)
        return repository.getVideoPost()
    }

}