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

    init {
        onAction(FeedAction.LoadNews)
    }

    fun onAction(action: FeedAction) {
        when (action) {
            FeedAction.LoadNews, FeedAction.Retry -> loadNews()
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getNewsUseCase()
            _uiState.update {
                it.copy(
                    articles = result.articles,
                    isFallback = result.isFallback,
                    isLoading = false,
                )
            }
        }
    }
}
