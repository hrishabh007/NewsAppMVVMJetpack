package com.app.newsappmvvmjetpack.presentation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.newsappmvvmjetpack.R

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem("home_screen", Icons.Filled.Home, "Home")
    object Category : NavigationItem("video_screen", Icons.Filled.AccountCircle, "Category")
    object Video : NavigationItem("category_screen", Icons.Filled.Search, "Video")
    object Favorite : NavigationItem("favorite_screen", Icons.Filled.Search, "Favorite")
    object NewsDetail : NavigationItem("news_detail_screen", Icons.Filled.Home, "NewsDetail")
}