package com.app.newsappmvvmjetpack.domain.usecase.get_news_detail

import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.toGetCategory
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.toGetNewsDetail
import com.app.newsappmvvmjetpack.domain.model.getCategories.GetCategory
import com.app.newsappmvvmjetpack.domain.model.getNewsDetail.GetNewsDetail
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<GetNewsDetail>> = flow {
        try {
            emit(Resource.Loading())
            val newsdetail = repository.getNewsDetail(id).toGetNewsDetail()
            emit(Resource.Success(newsdetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unExpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}