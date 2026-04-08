package com.rajedev.ainewsapp.domain.repository

import com.rajedev.ainewsapp.domain.model.Article

interface NewsRepository {
    suspend fun getLatestNews(): Result<List<Article>>
}
