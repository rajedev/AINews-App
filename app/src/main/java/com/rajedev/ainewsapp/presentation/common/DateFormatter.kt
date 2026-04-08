package com.rajedev.ainewsapp.presentation.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

private val INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
private val OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy · h:mm a", Locale.ENGLISH)

fun String.formatPubDate(): String = runCatching {
    val parsed = LocalDateTime.parse(this.trim(), INPUT_FORMAT)
    parsed.format(OUTPUT_FORMAT)
}.getOrElse { this }

fun String.toRelativeTime(): String = runCatching {
    val parsed = LocalDateTime.parse(this.trim(), INPUT_FORMAT)
    val now = LocalDateTime.now()
    val minutes = ChronoUnit.MINUTES.between(parsed, now)
    when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "${minutes}m ago"
        minutes < MINUTES_IN_DAY -> "${minutes / 60}h ago"
        minutes < MINUTES_IN_WEEK -> "${minutes / MINUTES_IN_DAY}d ago"
        else -> formatPubDate()
    }
}.getOrElse { this }

private const val MINUTES_IN_DAY = 1440L
private const val MINUTES_IN_WEEK = 10_080L
