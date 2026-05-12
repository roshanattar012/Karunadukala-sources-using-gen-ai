package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class WorkshopViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _isSubmitting = MutableStateFlow(false)
    val isSubmitting = _isSubmitting.asStateFlow()

    private val _submissionSuccess = MutableStateFlow(false)
    val submissionSuccess = _submissionSuccess.asStateFlow()

    fun registerForWorkshop(name: String, phone: String, email: String, artForm: String, message: String) {
        viewModelScope.launch {
            _isSubmitting.value = true
            val data = hashMapOf(
                "name" to name,
                "phone" to phone,
                "email" to email,
                "artForm" to artForm,
                "message" to message,
                "timestamp" to com.google.firebase.Timestamp.now()
            )
            try {
                firestore.collection("workshop_registrations").add(data).await()
                _submissionSuccess.value = true
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isSubmitting.value = false
            }
        }
    }
}
