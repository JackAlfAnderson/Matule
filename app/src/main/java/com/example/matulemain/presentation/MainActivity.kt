package com.example.matulemain.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.R
import com.example.matulemain.data.app.App
import com.example.matulemain.data.supabase.MainViewModel
import com.example.matulemain.presentation.category.CategoryScreen
import com.example.matulemain.presentation.details.DetailsScreen
import com.example.matulemain.presentation.favorite.FavoriteScreen
import com.example.matulemain.presentation.home.HomeScreen
import com.example.matulemain.presentation.onBoarding.OnBoardingScreen
import com.example.matulemain.presentation.popular.PopularScreen
import com.example.matulemain.presentation.signIn.SignInScreen
import com.example.matulemain.presentation.splash.SplashScreen
import com.example.matulemain.ui.theme.MatuleMainTheme
import com.example.matulemain.ui.theme.accent


val mainViewModel = MainViewModel(App.instance.baseManager)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleMainTheme {

                val navController = rememberNavController()
                var isBottom by remember { mutableStateOf(false) }
                var activeIcon by remember { mutableStateOf(1) }

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
                        activeIcon = 1
                        isBottom = true
                        HomeScreen(navController)
                    }
                    composable(route = "signIn") {
                        isBottom = false
                        SignInScreen(navController)
                    }
                    composable(route = "signUp") {
                        isBottom = false

                    }
                    composable(route = "category") {
                        isBottom = false
                        CategoryScreen(navController)
                    }
                    composable(route = "popular") {
                        isBottom = false
                        PopularScreen(navController)
                    }
                    composable(route = "favorite"){
                        activeIcon = 2
                        isBottom = true
                        FavoriteScreen(navController)
                    }
                    composable(route = "details") {
                        isBottom = false
                        DetailsScreen(navController)
                    }
                }
                if(isBottom){

                    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                        Icon(
                            painterResource(R.drawable.carticon),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .clickable { }
                        )
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
                                    navController.navigate("home")
                                }
                            )
                            Spacer(Modifier.width(40.dp))
                            Icon(
                                painter = painterResource(R.drawable.favoriteicon),
                                null,
                                tint = if(activeIcon == 2 ) accent else Color.Unspecified,
                                modifier = Modifier.clickable {
                                    activeIcon = 2
                                    navController.navigate("favorite")
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

            }
        }
    }
}
