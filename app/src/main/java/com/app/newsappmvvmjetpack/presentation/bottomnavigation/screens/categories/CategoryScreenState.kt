package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.categories

import com.app.newsappmvvmjetpack.domain.model.getCategories.GetCategory
import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings


data class CategoryScreenState(
    val isLoading: Boolean = false,
    val coins: GetCategory? = null,
    val error: String = ""
)