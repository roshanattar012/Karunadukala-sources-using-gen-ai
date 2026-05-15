package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunada.kala.data.local.entities.BookingEntity
import com.karunada.kala.data.repository.HeritageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkshopViewModel @Inject constructor(
    private val repository: HeritageRepository
) : ViewModel() {

    private val _isSubmitting = MutableStateFlow(false)
    val isSubmitting = _isSubmitting.asStateFlow()

    private val _submissionSuccess = MutableStateFlow(false)
    val submissionSuccess = _submissionSuccess.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun registerForWorkshop(name: String, phone: String, email: String, artForm: String, message: String) {
        viewModelScope.launch {
            _isSubmitting.value = true
            _error.value = null
            
            try {
                val booking = BookingEntity(
                    name = name,
                    phone = phone,
                    email = email,
                    artForm = artForm,
                    message = message
                )
                
                // HeritageRepository handles both local and remote sync
                repository.addBooking(booking)
                _submissionSuccess.value = true
            } catch (e: Exception) {
                _error.value = "Registration failed: ${e.localizedMessage}. Please try again later."
            } finally {
                _isSubmitting.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
