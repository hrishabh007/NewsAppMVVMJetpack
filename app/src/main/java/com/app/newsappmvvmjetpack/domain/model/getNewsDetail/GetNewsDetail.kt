package com.app.newsappmvvmjetpack.domain.model.getNewsDetail

import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.Image
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.Post
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.Related
import com.google.gson.annotations.SerializedName

data  class GetNewsDetail (
    val images: List<Image>,
    val post: Post,
    val related: List<Related>,
    val status: String
)
