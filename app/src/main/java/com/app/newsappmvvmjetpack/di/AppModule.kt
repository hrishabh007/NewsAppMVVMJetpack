package com.app.newsappmvvmjetpack.di


import com.app.newsappmvvmjetpack.data.remote.RetrofitService
import com.app.newsappmvvmjetpack.data.repository.NewsRepositoryImpl
import com.app.newsappmvvmjetpack.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): RetrofitService {
        return RetrofitService.getInstance()
    }


    @Provides
    @Singleton
    fun providesNewsRepository(api: RetrofitService): NewsRepository {
        return NewsRepositoryImpl(api)
    }
}