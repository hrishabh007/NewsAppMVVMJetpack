package com.app.newsappmvvmjetpack.presentation.newsDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.component.CategoryItem
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.categories.CategoryViewModel
import com.app.newsappmvvmjetpack.presentation.theme.NavigationBarMediumTheme

@Composable
fun NewsDetailScreen(navController: NavController, viewModel: NewsDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    NavigationBarMediumTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val columnCount = 3
            Box(modifier = Modifier.fillMaxWidth()) {
               state.newsDetail.let {
                   Text(text = state.newsDetail?.post?.newsTitle.toString())
               }
//                LazyColumn(modifier = Modifier) {
//                    state.coins?.let { it1 ->
//                        items(it1.categories.chunked(columnCount)) { rowItems ->
//                            Row {
//                                for (item in rowItems) {
//                                    CategoryItem(category = item, onItemClick = {})
//                                }
//                            }
//                        }
//
//                    }
//                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        style = TextStyle(color = MaterialTheme.colorScheme.error),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }
        }
    }
}