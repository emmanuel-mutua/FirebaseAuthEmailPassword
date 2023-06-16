package com.example.firebasesigninemailpassword.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firebasesigninemailpassword.navigation.NavGraphComposable
import com.example.firebasesigninemailpassword.ui.theme.FireBaseSignInEmailPasswordTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
     private val viewModel by viewModels<MainViewModel>()
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //has !account = signip screen
            //logged in = profile screen
            navController = rememberAnimatedNavController()


            FireBaseSignInEmailPasswordTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraphComposable(navController = navController)
                    AuthState()
                }
            }
        }
    }
}

@Composable
private fun AuthState() {

}
