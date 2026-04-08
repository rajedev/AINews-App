package com.rajedev.ainewsapp.domain.usecase

import com.rajedev.ainewsapp.domain.model.Article

data class NewsResult(
    val articles: List<Article>,
    val isFallback: Boolean,
)
