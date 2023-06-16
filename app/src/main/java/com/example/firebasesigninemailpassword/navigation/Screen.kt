package com.example.firebasesigninemailpassword.navigation

import com.example.firebasesigninemailpassword.core.PROFILE_SCREEN
import com.example.firebasesigninemailpassword.core.SIGN_IN_SCREEN
import com.example.firebasesigninemailpassword.core.SIGN_UP_SCREEN
import com.example.firebasesigninemailpassword.core.VERIFY_EMAIL_SCREEN

sealed class Screen(val route: String){
    object SignInScreen:Screen(SIGN_IN_SCREEN)
    object SignUpScreen:Screen(SIGN_UP_SCREEN)
    object ProfileScreen:Screen(PROFILE_SCREEN)
    object VerifyEmail:Screen(VERIFY_EMAIL_SCREEN)
}

