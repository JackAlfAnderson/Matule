package com.example.matulemain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.data.app.App
import com.example.matulemain.data.domain.models.Product
import com.example.matulemain.data.supabase.MainViewModel
import com.example.matulemain.presentation.home.CategoryItem
import com.example.matulemain.presentation.home.HomeScreen
import com.example.matulemain.presentation.home.SneakerScreen
import com.example.matulemain.presentation.onBoarding.OnBoardingScreen
import com.example.matulemain.presentation.signIn.SignInScreen
import com.example.matulemain.presentation.splash.SplashScreen
import com.example.matulemain.ui.theme.MatuleMainTheme
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back


val mainViewModel = MainViewModel(App.instance.baseManager)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleMainTheme {

                val navController = rememberNavController()
                var isBottom by remember { mutableStateOf(false) }

                NavHost(
                    navController,
                    startDestination = "splash"
                ){
                    composable(route = "splash") {
                        isBottom = false
                        SplashScreen(navController)
                    }
                    composable(route = "onBoarding") {
                        isBottom = false
                        OnBoardingScreen(navController)
                    }
                    composable(route = "home") {
                        isBottom = true
                        HomeScreen(mainViewModel, navController)
                    }
                    composable(route = "signIn") {
                        isBottom = false
                        SignInScreen(navController)
                    }
                    composable(route = "signUp") {
                        isBottom = false

                    }
                }
                if(isBottom){
                    BottomNavigation()
                }

            }
        }
    }
}

@Composable
private fun BottomNavigation() {

    var activeIcon by remember { mutableStateOf(0) }


    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Icon(
            painter = painterResource(R.drawable.bottomnavigation),
            null,
            tint = Color.Unspecified
        )
        Row(Modifier.padding(bottom = 30.dp)) {
            Icon(
                painter = painterResource(R.drawable.homeicon),
                null,
                tint = if(activeIcon == 1) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 1

                }
            )
            Spacer(Modifier.width(40.dp))
            Icon(
                painter = painterResource(R.drawable.favoriteicon),
                null,
                tint = if(activeIcon == 2 ) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 2

                }
            )
            Spacer(Modifier.width(140.dp))
            Icon(
                painter = painterResource(R.drawable.notificationicon),
                null,
                tint = if(activeIcon == 3) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 3

                }
            )
            Spacer(Modifier.width(40.dp))

            Icon(
                painter = painterResource(R.drawable.profileicon),
                null,
                tint = if(activeIcon == 4) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 4

                }
            )
        }
    }
}