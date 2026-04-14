package com.rajedev.ainewsapp.domain.usecase

import com.rajedev.ainewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(page: String? = null): NewsResult {
        val result = repository.getLatestNews(page)
        return if (result.isSuccess) {
            val page = result.getOrThrow()
            NewsResult(articles = page.articles, isFallback = false, nextPage = page.nextPage)
        } else {
            NewsResult(articles = SampleArticles.list, isFallback = true)
        }
    }
}
