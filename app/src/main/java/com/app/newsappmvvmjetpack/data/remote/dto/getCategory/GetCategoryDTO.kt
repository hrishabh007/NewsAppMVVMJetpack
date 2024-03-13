package com.app.newsappmvvmjetpack.data.remote.dto.getCategory


import com.google.gson.annotations.SerializedName

data class GetCategoryDTO(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("status")
    val status: String
)