package com.rajedev.ainewsapp.data.repository

import com.rajedev.ainewsapp.data.remote.api.NewsApiService
import com.rajedev.ainewsapp.data.remote.mapper.toDomain
import com.rajedev.ainewsapp.domain.model.NewsPage
import com.rajedev.ainewsapp.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    @Named("io") private val dispatcher: CoroutineDispatcher,
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
