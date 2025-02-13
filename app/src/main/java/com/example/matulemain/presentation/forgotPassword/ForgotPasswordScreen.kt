package com.example.matulemain.presentation.forgotPassword

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.matulemain.R
import com.example.matulemain.presentation.signIn.DialogWindow
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var isShow by remember { mutableStateOf(false) }

    if (showDialog){
        DialogForgotWindow("Проверьте Ваш Email", "Мы отправили код восстановления пароля на вашу электронную почту.", onDismissRequest = {
            navController.navigate("")
        })
    }

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

            Text("Забыл Пароль", fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Введите Свою Учетную Запись\n" +
                        " Для Сброса",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(35.dp))

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
            Spacer(Modifier.height(24.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent,

                    ),
                enabled = if (Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches()
                ) true else false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    navController.navigate("home")
                },
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Отправить")
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
                        navController.navigate("signUp")
                    }
            )
        }

    }
}

@Composable
fun DialogForgotWindow(text1: String, text2: String, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
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
                Card(
                    shape = RoundedCornerShape(100),
                    colors = CardDefaults.cardColors(
                        containerColor = accent
                    ),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                        Icon(painterResource(R.drawable.emailicon), null, tint = Color.Unspecified)
                    }
                }
                Spacer(Modifier.height(30.dp))

                Text(text1)
                Spacer(Modifier.height(10.dp))
                Text(text2, textAlign = TextAlign.Center)
            }
        }
    }

}


@Preview
@Composable
private fun ForgotPreview() {
    ForgotPasswordScreen(rememberNavController())
}