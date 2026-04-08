package com.rajedev.ainewsapp.presentation.ui.feed

sealed interface FeedAction {
    data object LoadNews : FeedAction
    data object Retry : FeedAction
}
