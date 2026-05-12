package com.karunada.kala.service

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton
import android.os.Bundle

@Singleton
class AnalyticsService @Inject constructor() {
    private val analytics = Firebase.analytics

    fun logEvent(name: String, params: Map<String, Any> = emptyMap()) {
        val bundle = Bundle().apply {
            params.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Double -> putDouble(key, value)
                    is Long -> putLong(key, value)
                    is Boolean -> putBoolean(key, value)
                }
            }
        }
        analytics.logEvent(name, bundle)
    }

    fun logScreenView(screenName: String) {
        logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, mapOf(
            FirebaseAnalytics.Param.SCREEN_NAME to screenName,
            FirebaseAnalytics.Param.SCREEN_CLASS to screenName
        ))
    }
}
