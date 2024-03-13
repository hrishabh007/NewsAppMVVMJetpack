package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.component.RecentListItem
import com.app.newsappmvvmjetpack.presentation.theme.NavigationBarMediumTheme
import com.app.newsappmvvmjetpack.presentation.util.ErrorMessage
import com.app.newsappmvvmjetpack.presentation.util.LoadingNextPageItem
import com.app.newsappmvvmjetpack.presentation.util.PageLoader
import kotlinx.coroutines.delay


@Composable
fun RecentPostScreen(navController: NavController, viewModel: RecentPostScreenViewModel = hiltViewModel()) {

    //val state = viewModel.state.value
   // val lastIndex = state.coins?.posts?.lastIndex
   // val items = state.coins?.posts
    val moviePagingItems: LazyPagingItems<RecentPost> = viewModel.moviesState.collectAsLazyPagingItems()
    NavigationBarMediumTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    item { Spacer(modifier = Modifier.padding(4.dp)) }

                    items(moviePagingItems.itemCount) { index ->
                        RecentListItem(
                            recentPost = moviePagingItems[index]!!,
                            onItemClick = {
                                // navController.navigate(AppScreen.DetailsScreen.route)
                            }
                        )

                    }
                    moviePagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                            }

                            loadState.refresh is LoadState.Error -> {
                                val error = moviePagingItems.loadState.refresh as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() })
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item { LoadingNextPageItem(modifier = Modifier) }
                            }

                            loadState.append is LoadState.Error -> {
                                val error = moviePagingItems.loadState.append as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier,
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() })
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.padding(4.dp)) }
//                    state.coins?.let {
//                         items(it.posts) { item: RecentPost ->
//                            RecentListItem(recentPost = item, onItemClick = {
//                               //navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}")
//                           })
//                       }
//                   }
                }
//                if (state.error.isNotBlank()) {
//                    Text(
//                        text = state.error,
//                        style = TextStyle(color = MaterialTheme.colorScheme.error),
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp)
//                            .align(Alignment.Center)
//                    )
//                }
//                if (state.isLoading) {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }

            }
        }
    }

}




