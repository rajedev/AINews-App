package com.rajedev.ainewsapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryPill(
    category: String,
    modifier: Modifier = Modifier,
) {
    val (backgroundColor, textColor, borderColor) = categoryColors(category)
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = backgroundColor,
        border = BorderStroke(width = 1.dp, color = borderColor),
    ) {
        Text(
            text = category.replaceFirstChar { it.uppercase() },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall.copy(color = textColor),
        )
    }
}

private fun categoryColors(category: String): Triple<Color, Color, Color> = when (category) {
    "technology" -> Triple(Color(0xFFDBEAFE), Color(0xFF1D4ED8), Color(0xFF93C5FD))
    "sports" -> Triple(Color(0xFFDCFCE7), Color(0xFF15803D), Color(0xFF86EFAC))
    "food" -> Triple(Color(0xFFFFE4E6), Color(0xFFBE123C), Color(0xFFFCA5A5))
    "science" -> Triple(Color(0xFFF3E8FF), Color(0xFF7E22CE), Color(0xFFD8B4FE))
    "tourism" -> Triple(Color(0xFFCCFBF1), Color(0xFF0F766E), Color(0xFF5EEAD4))
    else -> Triple(Color(0xFFF1F5F9), Color(0xFF475569), Color(0xFFCBD5E1))
}
