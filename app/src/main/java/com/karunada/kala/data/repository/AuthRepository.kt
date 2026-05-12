package com.karunada.kala.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await

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

    suspend fun signUp(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    val isUserLoggedIn: Boolean
        get() = firebaseAuth.currentUser != null
}
