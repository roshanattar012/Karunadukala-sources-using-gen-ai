package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunada.kala.data.HeritageSite
import com.karunada.kala.data.repository.HeritageRepository
import com.karunada.kala.service.AnalyticsService
import com.karunada.kala.service.GeminiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: HeritageRepository,
    private val geminiService: GeminiService,
    private val analyticsService: AnalyticsService
) : ViewModel() {

    private val _site = MutableStateFlow<HeritageSite?>(null)
    val site = _site.asStateFlow()

    private val _aiExplanation = MutableStateFlow<String?>(null)
    val aiExplanation = _aiExplanation.asStateFlow()

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading = _isAiLoading.asStateFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved = _isSaved.asStateFlow()

    fun loadSite(siteId: String) {
        analyticsService.logEvent("site_viewed", mapOf("site_id" to siteId))
        _aiExplanation.value = null // Clear old insights
        viewModelScope.launch {
            repository.isItemSaved(siteId).collect {
                _isSaved.value = it
            }
        }
        viewModelScope.launch {
            repository.getLocalHeritageData().collect { sites ->
                val found = sites.find { it.id == siteId }
                _site.value = found
                if (found != null && _aiExplanation.value == null) {
                    fetchAiExplanation(found.artForm)
                }
            }
        }
    }

    private fun fetchAiExplanation(artForm: String) {
        viewModelScope.launch {
            _isAiLoading.value = true
            _aiExplanation.value = geminiService.getArtExplanation(artForm)
            _isAiLoading.value = false
        }
    }

    fun toggleSave() {
        val currentSite = _site.value ?: return
        viewModelScope.launch {
            repository.toggleSave(currentSite.id, "ARTISAN", _isSaved.value)
        }
    }

    fun retryAiExplanation() {
        _site.value?.let { fetchAiExplanation(it.artForm) }
    }
}
