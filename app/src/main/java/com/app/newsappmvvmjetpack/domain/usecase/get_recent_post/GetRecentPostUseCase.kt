package com.app.newsappmvvmjetpack.domain.usecase.get_recent_post

import androidx.paging.PagingData
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.toGetRecentPost

import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import com.app.newsappmvvmjetpack.domain.usecase.BaseUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecentPostUseCase @Inject constructor(
    private val repository: NewsRepository
) :BaseUseCase<Unit, Flow<PagingData<RecentPost>>> {

    override suspend fun execute(input: Unit): Flow<PagingData<RecentPost>> {
      //  delay(3000)
        return repository.getRecentPost()
    }

}
//class GetRecentPostUseCase @Inject constructor(
//    private val repository: NewsRepository
//) {
//    operator fun invoke(): Flow<Resource<PagingData<RecentPost>>> = flow {
//        try {
//            emit(Resource.Loading())
//            val recentpost = repository.getRecentPost()
//            recentpost.collectLatest {
//                emit(Resource.Success(it))
//            }
//
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unExpected error occurred"))
//        } catch (e: IOException) {
//            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
//        }
//    }
//
//
//}