package com.app.newsappmvvmjetpack.data.remote.dto.getCategory


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_image")
    val categoryImage: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("cid")
    val cid: Int,
    @SerializedName("post_count")
    val postCount: Int
)