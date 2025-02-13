package com.example.matulemain.presentation.signUp

import android.util.Patterns
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.R
import com.example.matulemain.presentation.signIn.DialogWindow
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor

@Composable
fun SignUpScreen(navController: NavController) {
//values

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //boolean
    var checkBox by remember { mutableStateOf(false) }
    var isShow by remember { mutableStateOf(false) }
    var hider by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
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
            if (isShow){
                DialogWindow("Ошибка", "Почта некорректно введена")
            }

            Text("Регистрация", fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Заполните Свои Данные Или Продолжите Через Социальные Медиа ",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(30.dp))
            Text("Ваше имя", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(if (name.isEmpty()) "хххххххх" else "", color = hint)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = mainColor,
                    focusedContainerColor = mainColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(30.dp))
            Text("Email", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(if (email.isEmpty()) "xyz@gmail.com" else "", color = hint)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = mainColor,
                    focusedContainerColor = mainColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(26.dp))
            Text("Пароль", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    if (password.isEmpty()){
                        Text("••••••••", color = hint)
                    }
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = mainColor,
                    focusedContainerColor = mainColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                visualTransformation = if (hider) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = if(hider) painterResource(R.drawable.eye_open) else painterResource(
                            R.drawable.eye_slash),
                        null,
                        modifier = Modifier.clickable {
                            hider = !hider
                        }
                    )
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                Card(
                    modifier = Modifier.size(18.dp).clickable {
                        checkBox = !checkBox
                    },
                    colors = CardDefaults.cardColors(
                        mainColor
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    if (checkBox){
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(painterResource(R.drawable.vector), null)
                        }
                    }
                }
                Spacer(Modifier.width(12.dp))
                Text("Даю согласие на обработку персональных данных", textDecoration = TextDecoration.Underline, color = hint, modifier = Modifier.padding(end = 40.dp), fontSize = 16.sp)
            }
            Spacer(Modifier.height(24.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent,

                    ),
                enabled = if (Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches() && password.length > 6 && checkBox
                ) true else false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    navController.navigate("home")
                },
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Зарегестрироваться")
            }
            Spacer(Modifier.height(209.dp))
        }
    }
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier.padding(bottom = 50.dp)
        ) {
            Text("Есть аккаунт? ", fontSize = 16.sp, color = hint)
            Text("Войти", fontSize = 16.sp,
                modifier = Modifier
                    .clickable {
                        navController.navigate("signIn")
                    }
            )
        }

    }
}

@Preview
@Composable
private fun SignUpPrev() {
    SignUpScreen(rememberNavController())
}