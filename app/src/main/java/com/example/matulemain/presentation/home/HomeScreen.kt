package com.example.matulemain.presentation.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.matulemain.R
import com.example.matulemain.data.domain.models.Product
import com.example.matulemain.data.supabase.MainViewModel
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor

@Composable
fun HomeScreen(mainViewModel: MainViewModel, navController: NavController) {

    val categories = listOf(
        "Все",
        "outdoor",
        "tennis"
    )

    LaunchedEffect(Unit) {
        mainViewModel.getProducts()
    }

    val listOfProducts by mainViewModel.listOfProducts.collectAsState()

    var context = LocalContext.current

    val isShow: Boolean by mainViewModel.isShow.collectAsState()

    Column(
        modifier = Modifier
            .background(back)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Icon(
                        painterResource(R.drawable.mainhighlight),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(end = 130.dp, bottom = 30.dp)
                    )
                    Text(
                        "Главная",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painterResource(R.drawable.hamburger),
                        null,
                        tint = Color.Unspecified
                    )
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Icon(
                        painterResource(R.drawable.bagnotifiedicon),
                        null,
                        tint = Color.Unspecified
                    )
                }
            }
            Column(

            ) {
                Spacer(Modifier.height(21.dp))
                Row {
                    Icon(
                        painterResource(R.drawable.searchbutton),
                        null,
                        tint = Color.Unspecified
                    )
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Icon(
                            painterResource(R.drawable.settings),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(52.dp)
                        )
                    }

                }
                Spacer(Modifier.height(22.dp))
                Text("Категории", fontSize = 16.sp)
                Spacer(Modifier.height(19.dp))
                LazyRow {
                    items(categories) { category ->
                        CategoryItem(category)
                        Spacer(Modifier.width(16.dp))
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row {
                    Text("Популярное", fontSize = 16.sp)
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text("Все", fontSize = 16.sp, color = accent)
                    }
                }
                Spacer(Modifier.height(30.dp))
                LazyRow {
                    items(listOfProducts) { sneaker ->
                        SneakerScreen(sneaker, isShow)
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    val categories = listOf(
        "Все",
        "outdoor",
        "tennis"
    )


    Column(
        modifier = Modifier
            .background(back)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Icon(
                        painterResource(R.drawable.mainhighlight),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(end = 130.dp, bottom = 30.dp)
                    )
                    Text(
                        "Главная",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painterResource(R.drawable.hamburger),
                        null,
                        tint = Color.Unspecified
                    )
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Icon(
                        painterResource(R.drawable.bagnotifiedicon),
                        null,
                        tint = Color.Unspecified
                    )
                }
            }
            Column(

            ) {
                Spacer(Modifier.height(21.dp))
                Row {
                    Icon(
                        painterResource(R.drawable.searchbutton),
                        null,
                        tint = Color.Unspecified
                    )
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Icon(
                            painterResource(R.drawable.settings),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(52.dp)
                        )
                    }

                }
                Spacer(Modifier.height(22.dp))
                Text("Категории", fontSize = 16.sp)
                Spacer(Modifier.height(19.dp))
                LazyRow {
                    items(categories) { category ->
                        CategoryItem(category)
                        Spacer(Modifier.width(16.dp))
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row {
                    Text("Популярное", fontSize = 16.sp)
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text("Все", fontSize = 16.sp, color = accent)
                    }
                }
                Spacer(Modifier.height(30.dp))
                LazyRow {
                    item() {
                        SneakerScreen(
                            Product(
                                null,
                                "Air Hueir Maksimus",
                                null,
                                null,
                                null,
                                true,
                                null,
                                null
                            ),
                            isShow = true
                        )
                    }
                    item {
                        SneakerScreen(
                            Product(
                                null,
                                "Air Hueir Maksimus",
                                null,
                                null,
                                null,
                                true,
                                null,
                                null
                            ),
                            isShow = true
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row {
                    Text("Акции", fontSize = 16.sp)
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text("Все", fontSize = 16.sp, color = accent)
                    }
                }
                Spacer(Modifier.height(20.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(R.drawable.akciaimage),
                        null,
                        modifier = Modifier.size(width = 335.dp, height = 95.dp)
                    )
                }
            }

        }
    }
}


@Preview
@Composable
private fun SneakersPreview() {
    SneakerScreen(
        Product(
            null,
            "Air Hueir Maksimus",
            null,
            null,
            null,
            true,
            null,
            null
        ),
        isShow = true
    )
}

@Composable
fun SneakerScreen(product: Product, isShow: Boolean) {

    Column(
        Modifier.padding(end = 15.dp, bottom = 15.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                Modifier.size(
                    height = 182.dp,
                    width = 160.dp
                )
            ) {
                Box(

                ) {
                    Column(Modifier.padding(start = 9.dp, top = 9.dp)) {
                        Card(
                            shape = RoundedCornerShape(100),
                            modifier = Modifier.size(28.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = mainColor
                            )
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Icon(
                                    painterResource(R.drawable.favoriteicon),
                                    null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isShow) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp), // Увеличьте размер индикатора
                                color = mainColor // Установите цвет индикатора
                            )
                        } else {
                            AsyncImage(
                                model = product.image,
                                contentDescription = null,
                                modifier = Modifier.size(width = 142.dp, height = 70.dp)
                            )
                        }
                    }
                    Column(
                        Modifier.padding(top = 100.dp, start = 9.dp)
                    ) {
                        Text(
                            if (product.best_seller == true) "BEST SELLER" else "",
                            color = accent,
                            fontSize = 12.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            if (product.name!!.length > 13) product.name.take(13) + "..." else product.name,
                            color = hint,
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "₽ ${product.price} "
                        )
                    }

                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

                        Icon(
                            painterResource(R.drawable.plus_icon),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable {
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(text: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(width = 108.dp, height = 50.dp)
    ) {
        Box(
            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 12.sp)
        }

    }
}
