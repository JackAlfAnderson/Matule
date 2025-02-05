package com.example.matulemain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.data.app.App
import com.example.matulemain.data.supabase.MainViewModel
import com.example.matulemain.presentation.home.HomeScreen
import com.example.matulemain.presentation.onBoarding.OnBoardingScreen
import com.example.matulemain.presentation.signIn.SignInScreen
import com.example.matulemain.presentation.splash.SplashScreen
import com.example.matulemain.ui.theme.MatuleMainTheme


val mainViewModel = MainViewModel(App.instance.baseManager)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleMainTheme {

                val navController = rememberNavController()

                NavHost(
                    navController,
                    startDestination = "splash"
                ){
                    composable(route = "splash") {
                        SplashScreen(navController)
                    }
                    composable(route = "onBoarding") {
                        OnBoardingScreen(navController)
                    }
                    composable(route = "home") {
                        HomeScreen(mainViewModel, navController)
                    }
                    composable(route = "signIn") {
                        SignInScreen(navController)
                    }
                    composable(route = "signUp") {

                    }
                }
            }
        }
    }
}

