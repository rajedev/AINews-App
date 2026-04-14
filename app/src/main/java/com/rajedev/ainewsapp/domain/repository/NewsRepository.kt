package com.rajedev.ainewsapp.domain.repository

import com.rajedev.ainewsapp.domain.model.NewsPage

interface NewsRepository {
    suspend fun getLatestNews(page: String? = null): Result<NewsPage>
}
