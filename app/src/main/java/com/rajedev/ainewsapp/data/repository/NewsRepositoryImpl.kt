package com.rajedev.ainewsapp.data.repository

import com.rajedev.ainewsapp.data.remote.api.NewsApiService
import com.rajedev.ainewsapp.data.remote.mapper.toDomain
import com.rajedev.ainewsapp.domain.model.Article
import com.rajedev.ainewsapp.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    @Named("io") private val dispatcher: CoroutineDispatcher,
) : NewsRepository {

    override suspend fun getLatestNews(): Result<List<Article>> =
        withContext(dispatcher) {
            runCatching {
                api.getLatestNews()
                    .results
                    .orEmpty()
                    .map { it.toDomain() }
            }
        }
}
