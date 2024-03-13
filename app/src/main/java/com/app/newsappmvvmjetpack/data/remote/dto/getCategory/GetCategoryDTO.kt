package com.app.newsappmvvmjetpack.data.remote.dto.getCategory


import com.app.newsappmvvmjetpack.domain.model.getCategories.GetCategory
import com.google.gson.annotations.SerializedName

data class GetCategoryDTO(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("status")
    val status: String
)

fun GetCategoryDTO.toGetCategory(): GetCategory {
    return GetCategory(count = count, status = status, categories = categories.map {
        Category(
            categoryName = it.categoryName,
            categoryImage = it.categoryImage,
            cid = it.cid,
            postCount = it.postCount
        )
    })
}
//fun List<GetCategoryDTO>.mapFromListModel(): List<Category> {
//    return this.map {
//        it.mapFromEntity()
//    }
//}
//
//fun List<Movie>.mapFromListDomain(): List<MovieResponseDto> {
//    return this.map {
//        it.mapFromDomain()
//    }
//}