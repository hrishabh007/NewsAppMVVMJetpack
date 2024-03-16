package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.videos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.component.RecentListItem
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.component.VideoItem
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost.RecentPostScreenViewModel
import com.app.newsappmvvmjetpack.presentation.theme.NavigationBarMediumTheme
import com.app.newsappmvvmjetpack.presentation.util.ErrorMessage
import com.app.newsappmvvmjetpack.presentation.util.LoadingNextPageItem
import com.app.newsappmvvmjetpack.presentation.util.PageLoader

@Composable
fun VideoScreen(navController: NavController, viewModel: VideoPostScreenViewModel = hiltViewModel()) {
    val videoPagingItems: LazyPagingItems<RecentPost> = viewModel.videoState.collectAsLazyPagingItems()
    NavigationBarMediumTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)) {

                    item { Spacer(modifier = Modifier.padding(4.dp)) }

                    items(videoPagingItems.itemCount) { index ->
                        VideoItem(
                            recentPost = videoPagingItems[index]!!,
                            onItemClick = {
                                // navController.navigate(AppScreen.DetailsScreen.route)
                            }
                        )

                    }
                    videoPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                            }

                            loadState.refresh is LoadState.Error -> {
                                val error = videoPagingItems.loadState.refresh as LoadState.Error
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
                                val error = videoPagingItems.loadState.append as LoadState.Error
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