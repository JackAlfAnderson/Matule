package com.example.matulemain.presentation.myCart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.matulemain.R
import com.example.matulemain.data.app.App
import com.example.matulemain.domain.models.Product
import com.example.matulemain.presentation.mainViewModel
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.back

@Composable
fun MyCartScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        mainViewModel.getCartList(App.userId)
    }

    val listOfCartProducts by mainViewModel.listOfCart.collectAsState()

    Column(
        Modifier
            .background(back)
            .fillMaxSize()
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Корзина",
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
            Text("3 товара")
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(listOfCartProducts) {
                    CartItem(it)
                }
            }
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier
                .height(258.dp)
                .background(Color.White)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Box {
                Box(Modifier.fillMaxWidth()) {
                    Text("Сумма", fontSize = 16.sp, color = Color(0xFF707B81))
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text("₽131", fontSize = 16.sp)
                }
            }
            Spacer(Modifier.height(10.dp))
            Box {
                Box(Modifier.fillMaxWidth()) {
                    Text("Сумма", fontSize = 16.sp, color = Color(0xFF707B81))
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontSize = 18.sp,
                                )
                            ) {
                                append("₽")
                            }

                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp,
                                )
                            ) {
                                append("123")
                            }
                        },
                    )
                }
            }
            Spacer(Modifier.height(18.dp))
            Image(
                painterResource(R.drawable.cartline), null, Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            Spacer(Modifier.height(15.dp))
            Box {
                Box(Modifier.fillMaxWidth()) {
                    Text("Сумма", fontSize = 16.sp)
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text("₽131", fontSize = 16.sp, color = accent)
                }
            }
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent
                )
            ) {
                Text("Оформить заказ", color = Color.White)
            }
        }
    }
}

@Composable
fun CartItem(product: Product) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = back
                ),
                modifier = Modifier.size(87.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = product.image,
                        null,
                        modifier = Modifier
                            .width(86.dp)
                            .height(55.dp)
                    )
                }

            }
            Spacer(Modifier.width(30.dp))

            Column(Modifier.padding(bottom = 20.dp)) {
                Text(product.name.toString())
                Spacer(Modifier.height(6.dp))
                PriceText(product.price.toString())
            }
        }
    }
}

@Composable
fun PriceText(price: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 16.sp
                )
            ) {
                append("₽")
            }
            withStyle(
                SpanStyle(
                    fontSize = 14.sp
                )
            ) {
                append(price)

            }
        }
    )
}