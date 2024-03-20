package com.app.newsappmvvmjetpack.presentation.bottomnavigation

import com.app.newsappmvvmjetpack.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.newsappmvvmjetpack.common.ColorConstants
import com.app.newsappmvvmjetpack.common.CommonFunction
import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost.RecentPostScreen
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.categories.CategoryScreen
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.favorite.FavoriteScreen
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.videos.VideoScreen
import com.app.newsappmvvmjetpack.presentation.newsDetail.NewsDetailScreen
import com.app.newsappmvvmjetpack.presentation.theme.NavigationBarMediumTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NavigationBarMediumTheme {
                BottomNavigationBar()
            }

        }
    }
}

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var bottomBarState = rememberSaveable { (mutableStateOf(true)) }

// State of topBar, set state to false, if current page route is "car_details"
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    when (navBackStackEntry?.destination?.route) {
        Screens.NewsDetail.route -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
            //topBarState.value = true
        }

    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomAppBar() },
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,

            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Home.route) {
                RecentPostScreen(
                    navController
                )
            }
            composable(Screens.Category.route) {
                CategoryScreen(
                    navController
                )
            }
            composable(Screens.Video.route) {
                VideoScreen(
                    navController
                )
            }
            composable(Screens.Favorite.route) {

                FavoriteScreen(
                    navController
                )
            }
            composable(Screens.NewsDetail.route+ "/{${Constants.PARAM_ID}}") {
                NewsDetailScreen(
                    navController
                )

            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text("Android News App", color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = CommonFunction.getColorFromResId(
                context = context,
                resId = R.color.colorPrimary
            )
        ),
//        navigationIcon = {
//            IconButton(onClick = { /* Handle navigation back */ }) {
//                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//            }
//        },
        actions = {
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
            }
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
        }
    )
}

