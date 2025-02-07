package com.example.matulemain.presentation.category

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matulemain.R
import com.example.matulemain.data.app.App
import com.example.matulemain.data.domain.models.Product
import com.example.matulemain.data.supabase.MainViewModel
import com.example.matulemain.presentation.home.SneakerScreen
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back

@Composable
fun CategoryScreen(mainViewModel: MainViewModel, navController: NavController) {

    val categories = listOf(
        "Все",
        "Outdoor",
        "Tennis",
        "Running"
    )

    LaunchedEffect(Unit) {
        Log.d("prod", "я запустился раз")
        mainViewModel.getProducts()

    }

    val listOfProducts by mainViewModel.listOfProducts.collectAsState()

    var amILightning by remember { mutableStateOf(App.chosenCategory) }

    var listOfCategoryShoes by remember { mutableStateOf(listOf<Product>()) }

    listOfCategoryShoes = filterSneaker(listOfProducts, categories[amILightning])

    Column(
        modifier = Modifier
            .background(back)
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(10.dp))
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Category",
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
            }
            Spacer(Modifier.height(20.dp))
            Text("Категории", fontSize = 16.sp)
            Spacer(Modifier.height(20.dp))
            LazyRow {
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (amILightning == 0) accent else Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(width = 108.dp, height = 50.dp)
                            .clickable {
                                amILightning = 0
                            }
                    ) {
                        Box(
                            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                categories[0],
                                fontSize = 12.sp,
                                color = if (amILightning == 0) Color.White else Color.Black
                            )
                        }

                    }
                    Spacer(Modifier.width(16.dp))

                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (amILightning == 1) accent else Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(width = 108.dp, height = 50.dp)
                            .clickable {
                                amILightning = 1
                            }
                    ) {
                        Box(
                            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                categories[1],
                                fontSize = 12.sp,
                                color = if (amILightning == 1) Color.White else Color.Black
                            )
                        }

                    }
                    Spacer(Modifier.width(16.dp))

                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (amILightning == 2) accent else Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(width = 108.dp, height = 50.dp)
                            .clickable {
                                amILightning = 2
                            }
                    ) {
                        Box(
                            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                categories[2],
                                fontSize = 12.sp,
                                color = if (amILightning == 2) Color.White else Color.Black
                            )
                        }

                    }
                    Spacer(Modifier.width(16.dp))

                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (amILightning == 3) accent else Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(width = 108.dp, height = 50.dp)
                            .clickable {
                                amILightning = 3
                            }
                    ) {
                        Box(
                            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                categories[3],
                                fontSize = 12.sp,
                                color = if (amILightning == 3) Color.White else Color.Black
                            )
                        }

                    }
                    Spacer(Modifier.width(16.dp))

                }
            }
            Spacer(Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listOfCategoryShoes) {
                    SneakerScreen(it)
                }
            }


        }
    }
}

fun filterSneaker(list: List<Product>, targetCategory: String): List<Product> {
    val filteredList = mutableListOf<Product>()

    list.forEach {
        if (it.category == targetCategory) {
            filteredList.add(it)
        }
        if (targetCategory == "Все") {
            filteredList.add(it)
        }
    }

    return filteredList
}