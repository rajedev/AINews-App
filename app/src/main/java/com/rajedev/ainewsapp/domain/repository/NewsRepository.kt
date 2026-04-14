package com.rajedev.ainewsapp.domain.repository

import com.rajedev.ainewsapp.domain.model.Article

interface NewsRepository {
    suspend fun getLatestNews(page: String? = null): Result<Pair<List<Article>, String?>>
}
