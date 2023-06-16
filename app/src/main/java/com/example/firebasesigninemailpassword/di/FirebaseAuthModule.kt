package com.example.firebasesigninemailpassword.di

import com.example.firebasesigninemailpassword.data.repository.AuthRepositoryImpl
import com.example.firebasesigninemailpassword.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
class FirebaseAuthModule {
    @Provides
    fun provideAuthRepository():AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )
}