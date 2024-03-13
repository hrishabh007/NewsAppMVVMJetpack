package com.app.newsappmvvmjetpack.di


import com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost.RecentPostDataSource
import com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost.RecentPostDataSourceImpl
import com.app.newsappmvvmjetpack.data.datasource.remote.VideoPost.VideoPostDataSource
import com.app.newsappmvvmjetpack.data.datasource.remote.VideoPost.VideoPostDataSourceImpl
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

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: RetrofitService
    ): RecentPostDataSource {
        return RecentPostDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesVideoRemoteDataSource(
        api: RetrofitService
    ): VideoPostDataSource {
        return VideoPostDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(
        api: RetrofitService,
        movieRemoteDataSource: RecentPostDataSource,
        videoPostDataSource: VideoPostDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(api, movieRemoteDataSource, videoPostDataSource)
    }
}