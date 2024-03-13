package com.app.newsappmvvmjetpack.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.newsappmvvmjetpack.data.datasource.remote.RecentPost.RecentPostDataSource
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecentPostPagingSource  @Inject constructor (private val recentPostDataSource: RecentPostDataSource
) : PagingSource<Int, RecentPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecentPost> {
        return try {
            val currentPage = params.key ?: 1
            val movies = recentPostDataSource.getRecentPost(
              page = currentPage, count = 10
            )
            LoadResult.Page(
                data = movies.posts,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.posts.isEmpty()) null else movies.pages + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecentPost>): Int? {
        return state.anchorPosition
    }

}