package com.example.firebasesigninemailpassword.data.repository

import com.example.firebasesigninemailpassword.domain.model.Response
import com.example.firebasesigninemailpassword.domain.repository.AuthRepository
import com.example.firebasesigninemailpassword.domain.repository.AuthState
import com.example.firebasesigninemailpassword.domain.repository.ReloadUserResponse
import com.example.firebasesigninemailpassword.domain.repository.RevokeAccessResponse
import com.example.firebasesigninemailpassword.domain.repository.SendEmailVerification
import com.example.firebasesigninemailpassword.domain.repository.SignInResponse
import com.example.firebasesigninemailpassword.domain.repository.SignUpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth
) : AuthRepository {
    override val currentUser
        get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): SignUpResponse {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Response.Success(true)
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): SignInResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        }catch (e:Exception){
            Response.Failure(e)
        }

    }

    override suspend fun sendEmailVerification(): SendEmailVerification {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Response.Success(true)
        }catch (e:Exception){
            Response.Failure(e)
        }
    }

    override suspend fun reloadUserResponse(): ReloadUserResponse {
        return try {
            auth.currentUser?.reload()?.await()
            Response.Success(true)
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
       return try {
            auth.currentUser?.delete()?.await()
            Response.Success(true)
        }catch (e : Exception){
            Response.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)
}