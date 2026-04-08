package com.rajedev.ainewsapp.data.remote.mapper

import com.rajedev.ainewsapp.data.remote.dto.ArticleDto
import com.rajedev.ainewsapp.domain.model.Article

fun ArticleDto.toDomain(): Article = Article(
    id = articleId,
    link = link.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    categories = category.orEmpty(),
    pubDate = pubDate.orEmpty(),
    imageUrl = imageUrl,
)
