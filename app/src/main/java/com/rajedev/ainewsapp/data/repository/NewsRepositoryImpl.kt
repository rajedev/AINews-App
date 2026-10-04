package com.rajedev.ainewsapp.data.repository

import com.rajedev.ainewsapp.data.remote.api.NewsApiService
import com.rajedev.ainewsapp.data.remote.mapper.toDomain
import com.rajedev.ainewsapp.domain.model.NewsPage
import com.rajedev.ainewsapp.domain.repository.NewsRepository
import com.rajedev.ainewsapp.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : NewsRepository {

    override suspend fun getLatestNews(page: String?): Result<NewsPage> =
        withContext(dispatcher) {
            runCatching {
                val response = api.getLatestNews(page = page)
                val articles = response.results.orEmpty().map { it.toDomain() }
                NewsPage(articles = articles, nextPage = response.nextPage)
            }
        }
}
