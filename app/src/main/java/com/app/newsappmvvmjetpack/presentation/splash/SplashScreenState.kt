package com.app.newsappmvvmjetpack.presentation.splash

import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings


data class SplashScreenState(
    val isLoading: Boolean = false,
    val coins: GetSettings? = null,
    val error: String = ""
)