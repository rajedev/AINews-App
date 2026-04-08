package com.rajedev.ainewsapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    @SerialName("status") val status: String? = null,
    @SerialName("totalResults") val totalResults: Int? = null,
    @SerialName("results") val results: List<ArticleDto>? = null,
    @SerialName("nextPage") val nextPage: String? = null,
)
