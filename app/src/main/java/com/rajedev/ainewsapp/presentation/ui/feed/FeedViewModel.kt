package com.rajedev.ainewsapp.presentation.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajedev.ainewsapp.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    private var nextPage: String? = null

    init {
        onAction(FeedAction.LoadNews)
    }

    fun onAction(action: FeedAction) {
        when (action) {
            FeedAction.LoadNews, FeedAction.Retry -> loadNews()
            FeedAction.LoadMoreNews -> loadMoreNews()
        }
    }

    private fun loadMoreNews() {
        val currentNextPage = nextPage
        if (currentNextPage == null
            || _uiState.value.isFallback
            || _uiState.value.isLoadingMore
            || _uiState.value.isRefreshing) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }
            runCatching { getNewsUseCase(page = currentNextPage) }
                .onSuccess { result ->
                    nextPage = result.nextPage
                    _uiState.update {
                        it.copy(
                            articles = it.articles + result.articles,
                            hasMore = result.nextPage != null,
                            isLoadingMore = false,
                        )
                    }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoadingMore = false) }
                }
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            val hasArticles = _uiState.value.articles.isNotEmpty()
            if (hasArticles) {
                _uiState.update { it.copy(isRefreshing = true) }
            } else {
                _uiState.update { it.copy(isLoading = true) }
            }
            runCatching { getNewsUseCase() }
                .onSuccess { result ->
                    nextPage = result.nextPage
                    _uiState.update {
                        it.copy(
                            articles = result.articles,
                            isFallback = result.isFallback,
                            hasMore = result.nextPage != null,
                            isLoading = false,
                            isRefreshing = false,
                        )
                    }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false, isRefreshing = false) }
                }
        }
    }
}
