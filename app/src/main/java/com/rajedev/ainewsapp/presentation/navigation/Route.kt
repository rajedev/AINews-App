package com.rajedev.ainewsapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.rajedev.ainewsapp.domain.model.Article
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Feed : Route

    @Serializable
    data class ArticleDetail(val article: Article) : Route
}
