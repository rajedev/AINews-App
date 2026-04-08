package com.rajedev.ainewsapp.domain.usecase

import com.rajedev.ainewsapp.domain.model.Article

internal object SampleArticles {

    val list: List<Article> = listOf(
        Article(
            id = "sample_1",
            link = "https://newsdata.io",
            title = "AI Revolutionizes Healthcare Diagnostics in 2026",
            description = "Artificial intelligence systems are transforming how doctors diagnose " +
                "complex diseases. New models trained on millions of medical images now " +
                "outperform specialists in detecting early-stage cancers and rare conditions, " +
                "reducing diagnosis times from weeks to minutes.",
            categories = listOf("technology", "science"),
            pubDate = "2026-04-08 09:00:00",
            imageUrl = null,
        ),
        Article(
            id = "sample_2",
            link = "https://newsdata.io",
            title = "AI-Powered Coaching Apps Transform Amateur Sports Training",
            description = "A wave of artificial intelligence coaching applications is helping " +
                "amateur athletes train smarter. Using computer vision and biomechanics data, " +
                "these apps analyze movement in real time and provide feedback previously " +
                "available only to professional athletes.",
            categories = listOf("sports", "technology"),
            pubDate = "2026-04-08 07:30:00",
            imageUrl = null,
        ),
        Article(
            id = "sample_3",
            link = "https://newsdata.io",
            title = "Smart Kitchens: How AI Is Changing the Way We Cook",
            description = "From personalized recipe suggestions based on nutritional needs to " +
                "smart ovens that adjust temperature automatically, AI is making its way into " +
                "home kitchens. Food tech startups are raising record funding as consumers " +
                "embrace AI-assisted meal planning.",
            categories = listOf("food", "technology"),
            pubDate = "2026-04-07 18:00:00",
            imageUrl = null,
        ),
        Article(
            id = "sample_4",
            link = "https://newsdata.io",
            title = "AI Travel Concierge Services See Explosive Growth",
            description = "Travelers are increasingly turning to AI-powered concierge apps to " +
                "plan entire trips — from flights and hotels to day-by-day itineraries tailored " +
                "to personal interests. The tourism industry reports a 40% uptick in bookings " +
                "made through AI platforms.",
            categories = listOf("tourism", "technology"),
            pubDate = "2026-04-07 14:00:00",
            imageUrl = null,
        ),
        Article(
            id = "sample_5",
            link = "https://newsdata.io",
            title = "Researchers Develop AI That Predicts Protein Folding in Milliseconds",
            description = "Scientists at a leading research institute have announced a " +
                "breakthrough AI model capable of predicting the three-dimensional structure " +
                "of proteins in under a millisecond. The advance could dramatically accelerate " +
                "drug discovery for diseases that have resisted treatment for decades.",
            categories = listOf("science", "technology"),
            pubDate = "2026-04-07 10:00:00",
            imageUrl = null,
        ),
    )
}
