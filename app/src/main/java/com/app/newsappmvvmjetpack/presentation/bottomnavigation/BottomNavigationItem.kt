package com.app.newsappmvvmjetpack.presentation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Category",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Category.route
            ),
            BottomNavigationItem(
                label = "Video",
                icon = Icons.Filled.Search,
                route = Screens.Video.route
            ),
            BottomNavigationItem(
                label = "Favorite",
                icon = Icons.Filled.Search,
                route = Screens.Favorite.route
            ),

            )
    }
}