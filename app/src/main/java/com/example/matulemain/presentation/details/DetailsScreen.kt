package com.example.matulemain.presentation.details

import android.graphics.pdf.PdfDocument.Page
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
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.matulemain.R
import com.example.matulemain.data.domain.models.Product
import com.example.matulemain.mainViewModel
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back
import com.example.matulemain.ui.theme.hint

@Composable
fun DetailsScreen(navController: NavController) {

//    LaunchedEffect(
//        Unit
//    ) {
//        mainViewModel.getProducts()
//    }
//    val listOfProd = mainViewModel.listOfProducts.collectAsState()
//    var listOfProducts = listOfProd.value

    var pagerState by remember { mutableStateOf(1) }

    Column(
        Modifier
            .fillMaxSize()
            .background(back),
    ) {
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
            PagerItem()
        }

    }

    Box(Modifier.fillMaxSize().padding(start = 20.dp, top = 560.dp)) {
        LazyRow {
//        items(listOfProducts){
//            SneakerMiniItem(it, )
//        }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }
            item {
                SneakerMiniItem(Product(null, null, null, null, null, null, null, null),"")
            }

        }
    }

}

@Composable
fun PagerItem() {
    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        //prod name
        Text("Nike Air Max 270 Essential", fontSize = 26.sp, modifier = Modifier.padding(end = 90.dp).fillMaxWidth())
        Spacer(Modifier.height(13.dp))
        //prod category
        Text("Men's Shoes", fontSize = 16.sp, color = hint, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(13.dp))
        //prod price
        Text("₽179.39", fontSize = 24.sp,modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(14.dp))
        //image
        Box(contentAlignment = Alignment.Center) {
            //underline
            Image(painterResource(R.drawable.underline), null, modifier = Modifier.padding(top = 60.dp))
            //sneaker
            Image(
                painterResource(R.drawable.thirdsneaker),
                null,
                modifier = Modifier.size(width = 240.dp , height = 125.dp)
            )
//            AsyncImage(
//                model = product.image,
//                null,
//                modifier = Modifier.size(width = 240.dp , height = 125.dp)
//            )


        }

        Spacer(Modifier.height(126.dp))
        Text(
            "Вставка Max Air 270 обеспечивает непревзойденный комфорт в течение всего дня. Изящный дизайн ........",
            modifier = Modifier.padding(end = 40.dp)
        )
        Spacer(Modifier.height(5.dp))
        Text("Подробнее", color = accent, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
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

@Preview
@Composable
private fun PagerPreview() {
    PagerItem()
}

@Preview
@Composable
private fun SneakerMiniPreview() {
    SneakerMiniItem(
        Product(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        ""
    )
}

@Preview
@Composable
private fun DetailsPreview() {
    DetailsScreen(rememberNavController())
}