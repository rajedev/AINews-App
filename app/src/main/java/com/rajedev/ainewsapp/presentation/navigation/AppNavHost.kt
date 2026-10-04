package com.rajedev.ainewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.rajedev.ainewsapp.presentation.ui.feed.FeedScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Route.Feed)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
    ) { key: NavKey ->
        when (key) {
            is Route.Feed -> NavEntry(key) {
                FeedScreen(
                    onArticleClick = { article ->
                        // Navigation handled by FeedScreen's bottom sheet
                    },
                )
            }
            else -> NavEntry(key) {
                // Since Route is sealed with only Feed and ArticleDetail values,
                // and we handle Feed explicitly above, this else branch
                // effectively handles the ArticleDetail route.
                // The ArticleDetail route is shown as a bottom sheet in FeedScreen
                // rather than as a separate navigation destination.
            }
        }
    }
}