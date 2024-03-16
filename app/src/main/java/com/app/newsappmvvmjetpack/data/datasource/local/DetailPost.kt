package com.app.newsappmvvmjetpack.data.datasource.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class DetailPost(

    @SerializedName("cat_id")
    @PrimaryKey
    var catId: Int?,
    @SerializedName("category_name")
    var categoryName: String?,
    @SerializedName("comments_count")
    var commentsCount: Int?,
    @SerializedName("content_type")
    var contentType: String?,
    @SerializedName("news_date")
    var newsDate: String?,
    @SerializedName("news_description")
    var newsDescription: String?,
    @SerializedName("news_image")
    var newsImage: String?,
    @SerializedName("news_title")
    var newsTitle: String?,
    @SerializedName("nid")
    var nid: Int?,
    @SerializedName("video_id")
    var videoId: String?,
    @SerializedName("video_url")
    var videoUrl: String?
) : Parcelable