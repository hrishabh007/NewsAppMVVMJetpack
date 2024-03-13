package com.app.newsappmvvmjetpack.domain.usecase.get_category

import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.toGetCategory
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.toGetSettings
import com.app.newsappmvvmjetpack.domain.model.getCategories.GetCategory
import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUsecase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<GetCategory>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getCategories().toGetCategory()
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unExpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}