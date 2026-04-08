package com.rajedev.ainewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.rajedev.ainewsapp.presentation.ui.detail.DetailScreen
import com.rajedev.ainewsapp.presentation.ui.feed.FeedScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Route.Feed)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        modifier = modifier,
    ) { key ->
        when (key) {
            is Route.Feed -> NavEntry(key) {
                FeedScreen(
                    onArticleClick = { article ->
                        backStack.add(Route.ArticleDetail(article = article))
                    },
                )
            }
            is Route.ArticleDetail -> NavEntry(key) {
                DetailScreen(
                    route = key,
                    onBack = { backStack.removeLastOrNull() },
                )
            }
            else -> NavEntry(key) {}
        }
    }
}
