package com.app.newsappmvvmjetpack.presentation.newsDetail

import com.app.newsappmvvmjetpack.domain.model.getNewsDetail.GetNewsDetail


data class NewsDetailState
    (
    val isLoading: Boolean = false,
    val newsDetail: GetNewsDetail? = null,
    val error: String = ""
)