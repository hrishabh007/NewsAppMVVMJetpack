package com.app.newsappmvvmjetpack.domain.usecase.get_settings

import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.toGetSettings
import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetSettingsUsecase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<GetSettings>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getSettings().toGetSettings()
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unExpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}