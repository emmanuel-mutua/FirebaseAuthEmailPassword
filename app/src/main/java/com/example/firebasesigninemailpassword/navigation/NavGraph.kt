package com.example.firebasesigninemailpassword.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.firebasesigninemailpassword.navigation.Screen.*
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraphComposable(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = SignInScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }) {

        composable(SignInScreen.route) {}
        composable(SignUpScreen.route) {}
        composable(VerifyEmail.route) {}
        composable(ProfileScreen.route) {}

    }
}
