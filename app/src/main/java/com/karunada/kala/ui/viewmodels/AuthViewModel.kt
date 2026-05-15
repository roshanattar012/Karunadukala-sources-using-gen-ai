package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunada.kala.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val currentUser = repository.currentUser

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                repository.signIn(email, password)
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Login failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signUp(email: String, password: String, displayName: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                repository.signUp(email, password, displayName)
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Registration failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProfile(displayName: String) {
        viewModelScope.launch {
            try {
                repository.updateProfile(displayName)
            } catch (e: Exception) {
                _error.value = "Failed to update profile: ${e.localizedMessage}"
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                repository.signInWithGoogle(idToken)
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Google Sign-In failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}
