package com.karunada.kala.service

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.karunada.kala.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiService @Inject constructor() {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun getArtExplanation(artForm: String): String = withContext(Dispatchers.IO) {
        try {
            val response = generativeModel.generateContent(
                content {
                    text("Explain the significance of the Karnataka art form '$artForm' in 3 concise paragraphs. Highlight its cultural heritage and traditional value.")
                }
            )
            response.text ?: "Could not generate explanation at this time."
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}
