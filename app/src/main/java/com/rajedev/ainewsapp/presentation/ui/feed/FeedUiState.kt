package com.rajedev.ainewsapp.presentation.ui.feed

import androidx.compose.runtime.Immutable
import com.rajedev.ainewsapp.domain.model.Article

@Immutable
data class FeedUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isFallback: Boolean = false,
    val isRefreshing: Boolean = false,
)
