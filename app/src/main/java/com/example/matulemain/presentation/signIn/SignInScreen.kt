package com.example.matulemain.presentation.signIn

import android.util.Patterns
import android.widget.Space
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.matulemain.R
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor

@Composable
fun SignInScreen() {

    //values
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //boolean
    var isEnabled by remember { mutableStateOf(false) }
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
            if (isShow){
                DialogWindow("Ошибка", "Почта некорректно введена")
            }

            Text("Привет !", fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Заполните Свои Данные Или Продолжите Через Социальные Медиа ",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(35.dp))
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
                    Text(if (email.isEmpty()) "••••••••" else "", color = hint)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = mainColor,
                    focusedContainerColor = mainColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                visualTransformation = if (hider) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {

                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "Восстановить",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    },
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = hint
            )
            Spacer(Modifier.height(24.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent,

                    ),
                enabled = if (Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches() && password.length > 6
                ) true else false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {

                },
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Войти")
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
            Text("Вы впервые? ", fontSize = 16.sp, color = hint)
            Text("Создать пользователя", fontSize = 16.sp,
                modifier = Modifier
                    .clickable {

                    }
            )
        }

    }
}


@Composable
fun DialogWindow(error: String, text: String) {
    Dialog(
        onDismissRequest = {

        }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(error)
                Spacer(Modifier.height(16.dp))
                Text(text)
            }
        }
    }

}

@Preview
@Composable
private fun DialogWindowPreview() {
    DialogWindow("Ошибка", "Некорректная почта")
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreen()
}