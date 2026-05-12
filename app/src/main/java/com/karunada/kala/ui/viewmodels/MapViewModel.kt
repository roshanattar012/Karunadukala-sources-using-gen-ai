package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.karunada.kala.data.HeritageSite
import com.karunada.kala.data.repository.HeritageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: HeritageRepository
) : ViewModel() {

    private val _selectedSite = MutableStateFlow<HeritageSite?>(null)
    val selectedSite = _selectedSite.asStateFlow()

    val sites = repository.getLocalHeritageData()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectSite(site: HeritageSite?) {
        _selectedSite.value = site
    }
}
