package com.karunada.kala.service

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.karunada.kala.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class GeminiService @Inject constructor() {

    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    suspend fun getArtExplanation(artForm: String): String = withContext(Dispatchers.IO) {
        if (BuildConfig.GEMINI_API_KEY.isBlank() || BuildConfig.GEMINI_API_KEY == "your_gemini_key_here") {
            return@withContext "API Key not configured. Please add GEMINI_API_KEY to local.properties."
        }
        
        try {
            val response = generativeModel.generateContent(
                content {
                    text("You are a cultural historian specializing in Karnataka heritage. Explain the significance of '$artForm' in 3 concise, engaging paragraphs. Focus on its origins, cultural impact, and why it's a pride of Karnataka.")
                }
            )
            val text = response.text
            if (text.isNullOrBlank()) {
                "Our AI guide is taking a moment to reflect. Please try again or explore other art forms."
            } else {
                text.trim()
            }
        } catch (e: Exception) {
            Timber.e(e, "Gemini Error")
            val errorMessage = e.localizedMessage ?: ""
            when {
                errorMessage.contains("404") -> "The AI service is currently unavailable for this specific model. We're working on a fix."
                errorMessage.contains("429") -> "The AI is busy helping many users right now. Please try again in a few minutes."
                errorMessage.contains("Safety") -> "I can't generate that specific insight, but this art form is a vital part of Karnataka's history."
                else -> "Error: Could not connect to cultural intelligence. Check your internet or try again."
            }
        }
    }
}
