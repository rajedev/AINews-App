package com.rajedev.ainewsapp.data.remote.api

import com.rajedev.ainewsapp.BuildConfig
import com.rajedev.ainewsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("api/1/latest")
    suspend fun getLatestNews(
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String = "AI",
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String = "science,sports,technology,tourism,food",
        @Query("removeduplicate") removeDuplicate: Int = 1,
        @Query("excludefield") excludeField: String = EXCLUDED_FIELDS,
    ): NewsResponseDto

    companion object {
        private const val EXCLUDED_FIELDS =
            "source_id,source_name,source_url,source_icon,source_priority,creator," +
                "video_url,pubdatetz,country,ai_tag,sentiment,ai_org,sentiment_stats," +
                "ai_region,duplicate,ai_summary,keywords,content,language"
    }
}
