package com.app.newsappmvvmjetpack.presentation.newsDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.common.CommonFunction
import com.app.newsappmvvmjetpack.domain.model.getNewsDetail.GetNewsDetail


import com.app.newsappmvvmjetpack.presentation.newsDetail.component.ViewPagerSlider
import com.app.newsappmvvmjetpack.presentation.theme.NavigationBarMediumTheme
import com.app.newsappmvvmjetpack.presentation.util.commoncomponents.CustomToolBarJetpack

@Composable
fun NewsDetailScreen(
    navController: NavController,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var newsdetail = state.newsDetail?.post
    NavigationBarMediumTheme {
        Surface(
            modifier = Modifier.fillMaxSize().padding(top = 20.dp, bottom = 50.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            var currentIndex by remember { mutableStateOf(0) }
            Box(modifier = Modifier.fillMaxWidth()) {
                state.newsDetail.let {
                    //Text(text = state.newsDetail?.post?.newsTitle.toString())
                    if (it != null) {

                        Column(modifier = Modifier.fillMaxWidth()) {
                            CustomToolBarJetpack(
                                navController = navController,
                                title = it.post.categoryName,
                                back = true,
                                newActions = {

                                })
                            // ImageSlider(imageList = it.images)
                            ViewPagerSlider(list = it.images)
                            Text(
                                text = newsdetail!!.newsTitle,
                                fontFamily = CommonFunction.poppinsBold,
                                maxLines = 2,
                                style = TextStyle(fontSize = 16.sp),
                                overflow = TextOverflow.Ellipsis
                            )
                            WebViewComponent(newsDetail = it)
                        }

                    }
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

@Composable
fun WebViewComponent(newsDetail: GetNewsDetail) {
    //val context = LocalContext.current
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            displayPostDescription(context, this, newsDetail.post.newsDescription)
            // loadUrl(url)
        }
    })
}

@SuppressLint("SetJavaScriptEnabled")
fun displayPostDescription(activity: Context, webView: WebView, htmlData: String) {
    //   val sharedPref = SharedPref(activity)
    webView.setBackgroundColor(Color.TRANSPARENT)
    webView.getSettings().defaultTextEncodingName = "UTF-8"
    webView.setFocusableInTouchMode(false)
    webView.isFocusable = false
    if (true) {
        webView.setOnLongClickListener { v: View? -> true }
        webView.isLongClickable = false
    }
    webView.getSettings().javaScriptEnabled = true
    val webSettings = webView.getSettings()
//    if (sharedPref.getFontSize() === 0) {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_XSMALL
//    } else if (sharedPref.getFontSize() === 1) {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_SMALL
//    } else if (sharedPref.getFontSize() === 2) {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_MEDIUM
//    } else if (sharedPref.getFontSize() === 3) {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_LARGE
//    } else if (sharedPref.getFontSize() === 4) {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_XLARGE
//    } else {
//        webSettings.defaultFontSize = Constant.FONT_SIZE_MEDIUM
//    }
    val mimeType = "text/html; charset=UTF-8"
    val encoding = "utf-8"
    val bg_paragraph: String
    bg_paragraph = if (false) {
        "<style type=\"text/css\">body{color: #eeeeee;} a{color:#ffffff; font-weight:bold;}"
    } else {
        "<style type=\"text/css\">body{color: #000000;} a{color:#1e88e5; font-weight:bold;}"
    }
    val font_style_default =
        "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/custom_font.ttf\")}body {font-family: MyFont; font-size: medium; overflow-wrap: break-word; word-wrap: break-word; -ms-word-break: break-all; word-break: break-all; word-break: break-word; -ms-hyphens: auto; -moz-hyphens: auto; -webkit-hyphens: auto; hyphens: auto;}</style>"
    val text_default = ("<html><head>"
            + font_style_default
            + "<style>img{max-width:100%;height:auto;} figure{max-width:100%;height:auto;} iframe{width:100%;}</style> "
            + bg_paragraph
            + "</style></head>"
            + "<body>"
            + htmlData
            + "</body></html>")
    val text_rtl = ("<html dir='rtl'><head>"
            + font_style_default
            + "<style>img{max-width:100%;height:auto;} figure{max-width:100%;height:auto;} iframe{width:100%;}</style> "
            + bg_paragraph
            + "</style></head>"
            + "<body>"
            + htmlData
            + "</body></html>")
    if (false) {
        webView.loadDataWithBaseURL(null, text_rtl, mimeType, encoding, null)
    } else {
        webView.loadDataWithBaseURL(null, text_default, mimeType, encoding, null)
    }
    webView.setWebViewClient(object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (false) {
                if (url.startsWith("http://")) {
//                    val intent = Intent(
//                        activity,
//                        ActivityWebView::class.java
//                    )
//                    intent.putExtra("url", url)
//                    activity.startActivity(intent)
                }
                if (url.startsWith("https://")) {
//                    val intent = Intent(
//                        activity,
//                        ActivityWebView::class.java
//                    )
//                    intent.putExtra("url", url)
//                    activity.startActivity(intent)
                }
                if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png")) {
//                    val intent = Intent(
//                        activity,
//                        ActivityWebViewImage::class.java
//                    )
//                    intent.putExtra("image_url", url)
//                    activity.startActivity(intent)
                }
                if (url.endsWith(".pdf")) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(url))
                    activity.startActivity(intent)
                }
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                activity.startActivity(intent)
            }
            return true
        }
    })
    // val customViewContainer = activity.findViewById<FrameLayout>(R.id.customViewContainer)
    webView.setWebChromeClient(object : WebChromeClient() {
        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            super.onShowCustomView(view, callback)
            webView.visibility = View.INVISIBLE
            //   customViewContainer.visibility = View.VISIBLE
            //  customViewContainer.addView(view)
        }

        override fun onHideCustomView() {
            super.onHideCustomView()
            webView.visibility = View.VISIBLE
            //    customViewContainer.visibility = View.GONE
        }
    })
}