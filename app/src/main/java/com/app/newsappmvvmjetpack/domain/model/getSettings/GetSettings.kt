package com.app.newsappmvvmjetpack.domain.model.getSettings

import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.SettingsPost


data class GetSettings(
    val post: SettingsPost,
    val status: String
)