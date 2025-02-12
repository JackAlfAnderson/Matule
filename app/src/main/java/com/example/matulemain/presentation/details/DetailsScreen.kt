package com.example.matulemain.presentation.details

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import coil3.compose.AsyncImage
import com.example.matulemain.R
import com.example.matulemain.data.app.App
import com.example.matulemain.domain.models.Cart
import com.example.matulemain.domain.models.Product
import com.example.matulemain.presentation.mainViewModel
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.red

@Composable
fun DetailsScreen(navController: NavController) {

    LaunchedEffect(
        Unit
    ) {
        mainViewModel.getProducts()
    }
    val listOfProd = mainViewModel.listOfProducts.collectAsState()
    val listOfProducts = listOfProd.value
    val pagerState = rememberPagerState { listOfProducts.size }
    LaunchedEffect(Unit) {
        //pagerState.animateScrollToPage()
    }

    val listOfCart by mainViewModel.listOfCart.collectAsState()



    Column(
        Modifier
            .fillMaxSize()
            .background(back),
    ) {

        Spacer(Modifier.height(20.dp))
        Column(
            Modifier.padding(20.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "SneakerShop",
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
                Box(
                    Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painterResource(R.drawable.bagnotifiedicon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(44.dp)
                            .clickable {

                            }
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            HorizontalPager(pagerState) { page ->
                PagerItem(listOfProducts[page])
            }
        }

    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 455.dp)
    ) {
        LazyRow {
            items(listOfProducts) {
                SneakerMiniItem(it, name = listOfProducts[pagerState.currentPage].name.toString())
            }

        }
    }
    
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp, start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        var isLiked by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxWidth()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(100),
                modifier = Modifier.size(52.dp).clickable {
                    isLiked = !isLiked
                }
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        if (isLiked) painterResource(R.drawable.favoritefill) else painterResource(R.drawable.favoriteicon),
                        null,
                        tint = if (isLiked) red else Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }

            }

        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){

            var isAdd by remember { mutableStateOf(false) }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent
                ),
                onClick = {
                    isAdd = !isAdd
                    mainViewModel.getCartProductsList(App.userId)
                    if (listOfCart.find { it.user_id == App.userId && it.product_id == listOfProducts[pagerState.currentPage].id } == null){
                        mainViewModel.insertCart(Cart(user_id = App.userId, product_id = listOfProducts[pagerState.currentPage].id, quantity = 1))
                    }
                },
                modifier = Modifier
                    .height(52.dp)
                    .padding()
                    .width(245.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Box {
                    Box(
                        Modifier.fillMaxWidth()
                    ){
                        Icon(
                            painterResource(R.drawable.bagiconoutline),
                            null,
                            tint = Color.White
                        )
                    }
                    Box (
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(if(isAdd) "Добавлено" else "В Корзину", color = Color.White)
                    }
                }

            }
        }
    }

}


@Composable
fun PagerItem(product: Product) {

    var showMore by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //prod name
        Text(
            product.name.toString(), fontSize = 26.sp, modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(13.dp))
        //prod category
        Text(
            product.category.toString(),
            fontSize = 16.sp,
            color = hint,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(13.dp))
        //prod price
        Text("₽ ${product.price}", fontSize = 24.sp, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(14.dp))
        //image
        Box(contentAlignment = Alignment.Center) {
            //underline
            Image(
                painterResource(R.drawable.underline),
                null,
                modifier = Modifier.padding(top = 70.dp)
            )
            //sneaker
            AsyncImage(
                model = product.image,
                null,
                modifier = Modifier.size(width = 240.dp, height = 125.dp)
            )


        }

        Spacer(Modifier.height(126.dp))
        if (showMore){
            Text(
                product.description.toString(),
                modifier = Modifier
                    .padding(end = 40.dp)
                    .fillMaxWidth()
            )
        } else {
            Text(
                if (product.description!!.length > 29) product.description.take(26) + "..." else product.description.toString(),
                modifier = Modifier
                    .padding(end = 40.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(Modifier.height(5.dp))
        Text(
            "Подробнее",
            color = accent,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showMore = !showMore
                },
            textAlign = TextAlign.End
        )

    }
}

@Composable
fun SneakerMiniItem(product: Product, name: String) {
    Card(
        modifier = Modifier.size(56.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (name == product.name) accent else Color.White
        )
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = product.image,
                        null,
                        modifier = Modifier.size(width = 52.dp, height = 27.dp)
                    )
                }

            }
        }


    }
    Spacer(Modifier.width(14.dp))
}