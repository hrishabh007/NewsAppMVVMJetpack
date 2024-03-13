package com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost


import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import com.google.gson.annotations.SerializedName

data class GetRecentPostDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("count_total")
    val countTotal: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("posts")
    val posts: List<RecentPost>,
    @SerializedName("status")
    val status: String
)

fun GetRecentPostDTO.toGetRecentPost(): GetRecentPost {

    return GetRecentPost(count = count, countTotal = countTotal, pages = pages, status = status, posts = posts.map {
        RecentPost(
            catId = it.catId,
            categoryName = it.categoryName,
            commentsCount = it.commentsCount,
            contentType = it.contentType,
            newsDate = it.newsDate,
            newsDescription = it.newsDescription,
            newsImage = it.newsImage,
            newsTitle = it.newsTitle,
            nid = it.nid,
            videoId = it.videoId,
            videoUrl = it.videoUrl,
            viewCount = it.viewCount
        )
    })
}