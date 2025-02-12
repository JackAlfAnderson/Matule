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
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.matulemain.R
import com.example.matulemain.data.app.App
import com.example.matulemain.domain.models.Cart
import com.example.matulemain.domain.models.Favorite
import com.example.matulemain.domain.models.Product
import com.example.matulemain.presentation.mainViewModel
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor
import com.example.matulemain.ui.theme.red

@Composable
fun HomeScreen(navController: NavController) {

    val categories = listOf(
        "Все",
        "Outdoor",
        "Tennis",
        "Running"
    )

    LaunchedEffect(Unit) {
        mainViewModel.getProducts()
    }

    val listOfProducts by mainViewModel.listOfProducts.collectAsState()

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
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {

                        }
                    )
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Icon(
                        painterResource(R.drawable.bagnotifiedicon),
                        null,
                        tint = Color.Unspecified,
                    )
                }
            }
            Column(

            ) {
                Spacer(Modifier.height(21.dp))
                Box(Modifier.fillMaxWidth()) {
                    Box(Modifier.fillMaxWidth()) {
                        Icon(
                            painterResource(R.drawable.searchbutton),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .width(269.dp)
                                .clickable {
                                    navController.navigate("search")
                                }
                        )
                    }
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Icon(
                            painterResource(R.drawable.settings),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                }
                Spacer(Modifier.height(18.dp))
                Text("Категории", fontSize = 16.sp)
                Spacer(Modifier.height(19.dp))
                LazyRow {
                    items(categories) { category ->
                        CategoryItemHome(category, navController)
                        Spacer(Modifier.width(16.dp))
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row {
                    Text("Популярное", fontSize = 16.sp)
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text(
                            "Все",
                            fontSize = 16.sp,
                            color = accent,
                            modifier = Modifier.clickable {
                                navController.navigate("popular")
                            }
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
                if (listOfProducts.isNotEmpty()) {
                    LazyRow {
                        items(listOfProducts) { sneaker ->
                            SneakerScreen(sneaker, navController)
                            Spacer(Modifier.width(15.dp))
                        }
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.size(128.dp)
                    )
                }

                Spacer(Modifier.height(24.dp))
                Row {
                    Text("Акции", fontSize = 16.sp)
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text(
                            "Все",
                            fontSize = 16.sp,
                            color = accent,
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                }
                Spacer(Modifier.height(2.dp))
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


@Composable
fun SneakerScreen(product: Product, navController: NavController) {
    var isLiked by remember(product.id) {
        mutableStateOf(App.listOfFavorite.contains(Favorite(user_id = App.userId, product_id = product.id!!)))
    }

    val listOfCart by mainViewModel.listOfCart.collectAsState()

    Column(
        modifier = Modifier.clickable {
            navController.navigate("details")
        }
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
                                    if(isLiked) painterResource(R.drawable.favoritefill) else painterResource(R.drawable.favoriteicon),
                                    null,
                                    tint = if(isLiked) red else Color.Unspecified,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable {
                                            isLiked = !isLiked
                                            if (isLiked){
                                                mainViewModel.insertFavorite(Favorite(product_id = product.id!!, user_id = App.userId))
                                                App.listOfFavorite.add(Favorite(product_id = product.id, user_id = App.userId))
                                            } else {
                                                mainViewModel.deleteFavorite(App.userId, product.id!!)
                                                App.listOfFavorite.remove(Favorite(product_id = product.id, user_id = App.userId))
                                            }
                                        }
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
                        AsyncImage(
                            model = product.image,
                            contentDescription = null,
                            modifier = Modifier.size(width = 142.dp, height = 70.dp)
                        )

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
                                mainViewModel.getCartProductsList(App.userId)
                                if (listOfCart.find { it.user_id == App.userId && it.product_id == product.id } == null){
                                    mainViewModel.insertCart(Cart(user_id = App.userId, product_id = product.id, quantity = 1))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun sdfsdfs() {
    var activeIcon by remember { mutableStateOf(0) }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

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
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxWidth()
        )
        Row(Modifier.padding(bottom = 30.dp)) {
            Icon(
                painter = painterResource(R.drawable.homeicon),
                null,
                tint = if (activeIcon == 1) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 1

                }
            )
            Spacer(Modifier.width(40.dp))
            Icon(
                painter = painterResource(R.drawable.favoriteicon),
                null,
                tint = if (activeIcon == 2) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 2

                }
            )
            Spacer(Modifier.width(140.dp))
            Icon(
                painter = painterResource(R.drawable.notificationicon),
                null,
                tint = if (activeIcon == 3) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 3

                }
            )
            Spacer(Modifier.width(40.dp))

            Icon(
                painter = painterResource(R.drawable.profileicon),
                null,
                tint = if (activeIcon == 4) accent else Color.Unspecified,
                modifier = Modifier.clickable {
                    activeIcon = 4

                }
            )
        }
    }
}

@Composable
fun CategoryItemHome(text: String, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(width = 108.dp, height = 50.dp)
            .clickable {
                navController.navigate("category")
                when (text) {
                    "Все" -> App.chosenCategory = 0
                    "Outdoor" -> App.chosenCategory = 1
                    "Tennis" -> App.chosenCategory = 2
                    "Running" -> App.chosenCategory = 3

                }
            }
    ) {
        Box(
            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 12.sp)
        }

    }
}
