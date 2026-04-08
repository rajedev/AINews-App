package com.rajedev.ainewsapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("article_id") val articleId: String,
    @SerialName("link") val link: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("category") val category: List<String>? = null,
    @SerialName("datatype") val datatype: String? = null,
    @SerialName("pubDate") val pubDate: String? = null,
    @SerialName("fetched_at") val fetchedAt: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
)
