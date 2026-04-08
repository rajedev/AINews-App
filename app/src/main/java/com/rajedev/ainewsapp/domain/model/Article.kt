package com.rajedev.ainewsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val link: String,
    val title: String,
    val description: String,
    val categories: List<String>,
    val pubDate: String,
    val imageUrl: String?,
)
