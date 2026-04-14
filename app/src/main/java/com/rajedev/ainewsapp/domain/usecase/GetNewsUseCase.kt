package com.rajedev.ainewsapp.domain.usecase

import com.rajedev.ainewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(page: String? = null): NewsResult {
        val result = repository.getLatestNews(page)
        return if (result.isSuccess) {
            val (articles, nextPage) = result.getOrThrow()
            NewsResult(articles = articles, isFallback = false, nextPage = nextPage)
        } else {
            NewsResult(articles = SampleArticles.list, isFallback = true)
        }
    }
}
