package com.rajedev.ainewsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DecorativeBlob(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(BLOB_SIZE.dp)
            .blur(BLOB_BLUR_RADIUS.dp)
            .background(color.copy(alpha = BLOB_ALPHA), CircleShape),
    )
}

private const val BLOB_SIZE = 200
private const val BLOB_BLUR_RADIUS = 60
private const val BLOB_ALPHA = 0.35f
