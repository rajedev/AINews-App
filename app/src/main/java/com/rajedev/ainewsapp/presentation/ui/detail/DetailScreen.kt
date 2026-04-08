package com.rajedev.ainewsapp.presentation.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.rajedev.ainewsapp.domain.model.Article
import com.rajedev.ainewsapp.presentation.common.CategoryPill
import com.rajedev.ainewsapp.presentation.common.GlassCard
import com.rajedev.ainewsapp.presentation.common.GradientButton
import com.rajedev.ainewsapp.presentation.common.formatPubDate
import com.rajedev.ainewsapp.presentation.common.toRelativeTime
import com.rajedev.ainewsapp.presentation.navigation.Route
import com.rajedev.ainewsapp.ui.theme.FeedGradientBackground

@Composable
fun DetailScreen(
    route: Route.ArticleDetail,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val article = route.article
    val context = LocalContext.current
    var isWebViewVisible by remember { mutableStateOf(false) }

    DetailContent(
        article = article,
        isWebViewVisible = isWebViewVisible,
        onBack = onBack,
        onToggleWebView = { isWebViewVisible = !isWebViewVisible },
        onOpenExternalBrowser = {
            val intent = Intent(Intent.ACTION_VIEW, article.link.toUri())
            context.startActivity(intent)
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailContent(
    article: Article,
    isWebViewVisible: Boolean,
    onBack: () -> Unit,
    onToggleWebView: () -> Unit,
    onOpenExternalBrowser: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = article.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White.copy(alpha = 0.85f),
                ),
            )
        },
        containerColor = Color.Transparent,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(FeedGradientBackground),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding() + 24.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    AsyncImage(
                        model = article.imageUrl?.ifEmpty { null },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(HERO_IMAGE_HEIGHT.dp),
                    )
                }
                item {
                    GlassCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = article.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                    }
                }
                item {
                    GlassCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = article.description,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
                item {
                    MetaInfoCard(
                        categories = article.categories,
                        pubDate = article.pubDate,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
                item {
                    GradientButton(
                        text = if (isWebViewVisible) "Close Article" else "Open Full Article",
                        onClick = onToggleWebView,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )
                }
                item {
                    AnimatedVisibility(
                        visible = isWebViewVisible,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut(),
                    ) {
                        ArticleWebView(
                            url = article.link,
                            onOpenExternal = onOpenExternalBrowser,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaInfoCard(
    categories: List<String>,
    pubDate: String,
    modifier: Modifier = Modifier,
) {
    GlassCard(modifier = modifier) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(categories) { category ->
                CategoryPill(category = category)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = pubDate.formatPubDate(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = pubDate.toRelativeTime(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ArticleWebView(
    url: String,
    onOpenExternal: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GlassCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = url.toUri().host.orEmpty(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(
                onClick = onOpenExternal,
                modifier = Modifier.size(ICON_BUTTON_SIZE.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = "Open in browser",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(WEB_VIEW_HEIGHT.dp),
        )
    }
}

private const val HERO_IMAGE_HEIGHT = 220
private const val WEB_VIEW_HEIGHT = 480
private const val ICON_BUTTON_SIZE = 36
