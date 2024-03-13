package com.app.newsappmvvmjetpack.domain.usecase.get_recent_post

import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.toGetRecentPost

import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecentPostUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(page: Int, count: Int): Flow<Resource<GetRecentPost>> = flow {
        try {
            emit(Resource.Loading())
            val recentpost = repository.getRecentPost(page = page, count = count).toGetRecentPost()
            emit(Resource.Success(recentpost))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unExpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}