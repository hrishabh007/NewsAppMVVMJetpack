package com.app.newsappmvvmjetpack.presentation.bottomnavigation

sealed class Screens(val route : String) {
    object Home : Screens("home_screen")
    object Video : Screens("video_screen")
    object Category : Screens("category_screen")
    object Favorite : Screens("favorite_screen")

    object NewsDetail : Screens("news_detail_screen")
}