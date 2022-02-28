package com.example.workapplication.api.model

import com.google.gson.annotations.SerializedName

data class NewsObj(
    val updateTime: String?,
    @SerializedName("News") val news: List<News>
)

data class News(
    @SerializedName("chtmessage") val chtMessage: String?,
    @SerializedName("engmessage") val engMessage: String?,
    @SerializedName("starttime") val startTime: String?,
    @SerializedName("endtime") val endTime: String?,
    @SerializedName("updatetime") val updateTime: String?,
    val content: String?,
    val url: String?
)