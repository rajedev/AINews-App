package com.rajedev.ainewsapp.domain.usecase

import com.rajedev.ainewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(): NewsResult {
        val result = repository.getLatestNews()
        return if (result.isSuccess) {
            NewsResult(articles = result.getOrThrow(), isFallback = false)
        } else {
            NewsResult(articles = SampleArticles.list, isFallback = true)
        }
    }
}
