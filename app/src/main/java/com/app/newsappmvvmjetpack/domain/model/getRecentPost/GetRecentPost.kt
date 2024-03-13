package com.app.newsappmvvmjetpack.domain.model.getRecentPost

import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost


data class GetRecentPost(
    val count: Int,
    val countTotal: Int,
    val pages: Int,
    val posts: List<RecentPost>,
    val status: String
)