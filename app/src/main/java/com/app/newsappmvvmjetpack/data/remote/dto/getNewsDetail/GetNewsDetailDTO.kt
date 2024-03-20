package com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail


import com.app.newsappmvvmjetpack.domain.model.getNewsDetail.GetNewsDetail
import com.google.gson.annotations.SerializedName

data class GetNewsDetailDTO(
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("post")
    val post: Post,
    @SerializedName("related")
    val related: List<Related>,
    @SerializedName("status")
    val status: String
)

fun GetNewsDetailDTO.toGetNewsDetail(): GetNewsDetail {
    return GetNewsDetail(
        status = status,
        post = Post(
            catId = post.catId,
            categoryName = post.categoryName,
            commentsCount = post.commentsCount,
            contentType = post.contentType,
            newsTitle = post.newsTitle,
            newsDate = post.newsDate,
            newsDescription = post.newsDescription,
            newsImage = post.newsImage,
            nid = post.nid,
            videoUrl = post.videoUrl,
            videoId = post.videoId,
            viewCount = post.viewCount
        ), images = images.map {
            Image(
                contentType = it.contentType,
                imageName = it.imageName,
                nid = it.nid,
                videoId = it.videoId,
                videoUrl = it.videoUrl
            )
        }, related = related.map {
            Related(
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
        }
    )
}