package com.example.matulemain.presentation.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matulemain.R
import com.example.matulemain.presentation.main.mainViewModel
import com.example.matulemain.presentation.home.SneakerScreen
import com.example.matulemain.ui.theme.back

@Composable
fun PopularScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        mainViewModel.getProducts()
    }

    val listOfProducts by mainViewModel.listOfProducts.collectAsState()


    Column(
        Modifier.fillMaxSize().background(back)
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Популярное",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painterResource(R.drawable.backicon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {
                            navController.navigate("home")
                        }
                    )
                }
                Box (
                    Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                ){
                    Card(
                        shape = RoundedCornerShape(100),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painterResource(R.drawable.heart),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        navController.navigate("favorite")
                                    }
                            )
                        }

                    }

                }
            }
            Spacer(Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listOfProducts) {
                    SneakerScreen(it, navController)
                }
            }
        }
    }
}
