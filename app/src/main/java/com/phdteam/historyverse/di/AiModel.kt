package com.phdteam.historyverse.di

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.phdTeam.HistoryVerse.BuildConfig
import com.phdteam.historyverse.data.network.service.GeminiApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val AiModel =  module {
    single {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.API_KEY,
            generationConfig = generationConfig {
                maxOutputTokens = 1000
            }
        )
    }
    singleOf(::GeminiApi)
}