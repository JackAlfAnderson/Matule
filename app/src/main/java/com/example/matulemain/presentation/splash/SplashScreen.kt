package com.example.matulemain.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.R
import com.example.matulemain.ui.theme.accent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {


    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("onBoarding")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(accent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(R.drawable.splashicon), tint = Color.Unspecified, contentDescription =  null, modifier = Modifier.size(129.dp))
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}