package com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("content_type")
    val contentType: String,
    @SerializedName("image_name")
    val imageName: String,
    @SerializedName("nid")
    val nid: Int,
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("video_url")
    val videoUrl: String
)