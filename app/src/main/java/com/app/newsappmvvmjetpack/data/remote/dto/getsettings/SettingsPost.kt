package com.app.newsappmvvmjetpack.data.remote.dto.getsettings


import com.google.gson.annotations.SerializedName

data class SettingsPost(
    @SerializedName("api_key")
    val apiKey: String,
    @SerializedName("app_fcm_key")
    val appFcmKey: Int,
    @SerializedName("comment_approval")
    val commentApproval: String,
    @SerializedName("fcm_notification_topic")
    val fcmNotificationTopic: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("login_feature")
    val loginFeature: String,
    @SerializedName("more_apps_url")
    val moreAppsUrl: String,
    @SerializedName("onesignal_app_id")
    val onesignalAppId: String,
    @SerializedName("onesignal_rest_api_key")
    val onesignalRestApiKey: String,
    @SerializedName("package_name")
    val packageName: String,
    @SerializedName("privacy_policy")
    val privacyPolicy: String,
    @SerializedName("protocol_type")
    val protocolType: String,
    @SerializedName("providers")
    val providers: String,
    @SerializedName("publisher_info")
    val publisherInfo: String,
    @SerializedName("video_menu")
    val videoMenu: String,
    @SerializedName("youtube_api_key")
    val youtubeApiKey: Int
)