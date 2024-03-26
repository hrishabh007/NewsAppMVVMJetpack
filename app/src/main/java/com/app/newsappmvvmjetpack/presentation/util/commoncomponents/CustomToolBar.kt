package com.app.newsappmvvmjetpack.presentation.util.commoncomponents

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.common.CommonFunction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar(
    navController: NavController,
    title: String,
    back: Boolean,
    newActions: @Composable RowScope.() -> Unit = {}
) {
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier,
        title = { Text(title, color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = CommonFunction.getColorFromResId(
                context = context,
                resId = R.color.colorPrimary
            )
        ),
        navigationIcon = {
            if (back) {
                IconButton(onClick = {
                    navController.popBackStack()
                    /* Handle navigation back */
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }

        },
        actions = newActions
    )
}
