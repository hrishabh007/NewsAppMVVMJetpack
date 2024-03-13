package com.app.newsappmvvmjetpack.domain.model.getCategories

import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.Category
import com.google.gson.annotations.SerializedName


data class GetCategory(
    val categories: List<Category>,
    val count: Int,
    val status: String
)