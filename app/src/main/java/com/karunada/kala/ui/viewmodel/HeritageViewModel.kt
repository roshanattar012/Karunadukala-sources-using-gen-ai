package com.karunada.kala.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunada.kala.data.*
import com.karunada.kala.data.local.entities.SavedEntity
import com.karunada.kala.data.repository.HeritageRepository
import com.karunada.kala.domain.usecase.GetHeritageSitesUseCase
import com.karunada.kala.domain.usecase.RefreshCulturalDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeritageViewModel @Inject constructor(
    private val getHeritageSitesUseCase: GetHeritageSitesUseCase,
    private val refreshCulturalDataUseCase: RefreshCulturalDataUseCase,
    private val repository: HeritageRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val heritageSites: StateFlow<List<HeritageSite>> = getHeritageSitesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val artForms: StateFlow<List<ArtForm>> = repository.getLocalArtForms()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val events: StateFlow<List<ArtEvent>> = repository.getLocalEvents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val products: StateFlow<List<Product>> = repository.getLocalProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedItems: StateFlow<List<SavedEntity>> = repository.getSavedItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // First try to load from local DB, then trigger a background refresh
        viewModelScope.launch {
            repository.refreshAllData() 
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshCulturalDataUseCase()
            _isRefreshing.value = false
        }
    }
}
