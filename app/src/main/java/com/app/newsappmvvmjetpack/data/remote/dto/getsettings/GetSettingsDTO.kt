package com.app.newsappmvvmjetpack.data.remote.dto.getsettings


import com.app.newsappmvvmjetpack.domain.model.getSettings.GetSettings
import com.google.gson.annotations.SerializedName

data class GetSettingsDTO(
    @SerializedName("post")
    val post: SettingsPost,
    @SerializedName("status")
    val status: String
)

fun GetSettingsDTO.toGetSettings(): GetSettings {

    var settingsPost = SettingsPost(
        apiKey = post.apiKey,
        appFcmKey = post.appFcmKey,
        commentApproval = post.commentApproval,
        fcmNotificationTopic = post.fcmNotificationTopic,
        id = post.id,
        lastUpdate = post.lastUpdate,
        loginFeature = post.loginFeature,
        moreAppsUrl = post.moreAppsUrl,
        onesignalAppId = post.onesignalAppId,
        onesignalRestApiKey = post.onesignalRestApiKey,
        packageName = post
            .packageName,
        privacyPolicy = post.privacyPolicy,
        protocolType = post.protocolType,
        providers = post.providers,
        publisherInfo = post.publisherInfo,
        videoMenu = post.videoMenu,
        youtubeApiKey = post.youtubeApiKey
    )
    return GetSettings(post = settingsPost, status = status)
}