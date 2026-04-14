package com.rajedev.ainewsapp.domain.model

data class NewsPage(
    val articles: List<Article>,
    val nextPage: String?,
)
