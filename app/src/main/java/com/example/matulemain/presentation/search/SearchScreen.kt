package com.example.matulemain.presentation.search

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.R
import com.example.matulemain.presentation.home.SneakerScreen
import com.example.matulemain.presentation.mainViewModel
import com.example.matulemain.ui.theme.back

@Composable
fun SearchScreen(navController: NavController) {

    var search by remember { mutableStateOf("") }
    val searchHistory by remember { mutableStateOf(mutableListOf<String>()) }
    var isFocused by remember { mutableStateOf(false) }

    val listOfProducts by mainViewModel.listOfProducts.collectAsState()

// Фильтрация товаров по названию, игнорируя регистр
    val filteredProducts = listOfProducts.filter {
        it.name!!.lowercase().contains(search.lowercase())
    }

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
                        "Поиск",
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
            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(14.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search // Устанавливаем действие "Поиск"
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Действие при нажатии на "Поиск" или "Готово"
                        searchHistory.add(search)
                        search = ""
                    }
                ),
                singleLine = true, // Запрещаем перенос строки
                label = {

                    if (!isFocused) {
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.searchline),
                                null,
                                tint = Color.Unspecified
                            )
                            Spacer(Modifier.width(12.dp))
                            Text("Поиск", fontSize = 12.sp)
                        }
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {

                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.linesearch),
                                    null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .height(22.dp)
                                        .width(5.dp)
                                )
                                Spacer(Modifier.width(12.dp))
                                Icon(
                                    painter = painterResource(R.drawable.microicon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }

                        }
                    }


                },
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        isFocused =
                            focusState.isFocused // Обновляем переменную isFocused, когда фокус изменяется
                    }
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            if (search.isEmpty()) {
                LazyColumn {
                    items(searchHistory) {
                        SearchItem(it)
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(filteredProducts) {
                        SneakerScreen(it, navController)
                    }
                }
            }

        }
    }
}

@Composable
fun SearchItem(text: String) {
    Spacer(Modifier.height(16.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.recenticon),
            null,
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(12.dp))
        Text(text)
    }
}

@Preview
@Composable
private fun SearchPreview() {
    SearchScreen(rememberNavController())
}