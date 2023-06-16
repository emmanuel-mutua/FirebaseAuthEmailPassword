package com.example.firebasesigninemailpassword.domain.repository

import com.example.firebasesigninemailpassword.domain.model.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias AuthState = StateFlow<Boolean>
typealias SendEmailVerification = Response<Boolean>

interface AuthRepository{
    val currentUser : FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password : String) : SignUpResponse
    suspend fun firebaseSignInWithEmailAndPassword(email : String, password: String): SignInResponse
    suspend fun sendEmailVerification(): SendEmailVerification
    suspend fun reloadUserResponse() : ReloadUserResponse
    suspend fun revokeAccess() :RevokeAccessResponse

    fun signOut()
    fun getAuthState(viewModelSCope : CoroutineScope): AuthState

}