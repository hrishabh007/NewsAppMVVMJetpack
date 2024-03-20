package com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("cat_id")
    val catId: Int,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("comments_count")
    val commentsCount: Int,
    @SerializedName("content_type")
    val contentType: String,
    @SerializedName("news_date")
    val newsDate: String,
    @SerializedName("news_description")
    val newsDescription: String,
    @SerializedName("news_image")
    val newsImage: String,
    @SerializedName("news_title")
    val newsTitle: String,
    @SerializedName("nid")
    val nid: Int,
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("view_count")
    val viewCount: Int
)