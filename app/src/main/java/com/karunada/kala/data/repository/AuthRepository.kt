package com.karunada.kala.data.repository

import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    private val _currentUser = MutableStateFlow(firebaseAuth.currentUser)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    init {
        firebaseAuth.addAuthStateListener {
            _currentUser.value = it.currentUser
        }
    }

    suspend fun signIn(email: String, password: String): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(email: String, password: String, displayName: String? = null): AuthResult {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        displayName?.let { updateProfile(it) }
        return result
    }

    suspend fun updateProfile(displayName: String) {
        val user = firebaseAuth.currentUser ?: return
        val profileUpdates = userProfileChangeRequest {
            this.displayName = displayName
        }
        user.updateProfile(profileUpdates).await()
        user.reload().await() // Ensure we have latest data
        _currentUser.value = firebaseAuth.currentUser
    }

    suspend fun signInWithGoogle(idToken: String): AuthResult {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return firebaseAuth.signInWithCredential(credential).await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    val isUserLoggedIn: Boolean
        get() = firebaseAuth.currentUser != null
}
