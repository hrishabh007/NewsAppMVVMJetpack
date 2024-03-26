package com.app.newsappmvvmjetpack.presentation.util.commoncomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.common.CommonFunction
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.component.RecentListItem

@Composable
fun CustomToolBarJetpack(
    navController: NavController?,
    title: String,
    back: Boolean,
    newActions: @Composable RowScope.() -> Unit = {}
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth().height(50.dp)
            .background(CommonFunction.getColorFromResId(context = context, resId = R.color.colorPrimary))
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            if (back) {
                IconButton(onClick = {
                    navController?.popBackStack()
                    /* Handle navigation back */
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = CommonFunction.getColorFromResId(context = context, resId = R.color.white)
                    )
                }
            }
            Text(
                title,
                style = TextStyle(color = CommonFunction.getColorFromResId(context = context, resId = R.color.white))
            )

        }
    }


}

@Preview
@Composable
fun CustomToolBarJetpackPreview() {

    CustomToolBarJetpack(
        navController = null, title = "Android News App", back = true, newActions = {}
    )
}

