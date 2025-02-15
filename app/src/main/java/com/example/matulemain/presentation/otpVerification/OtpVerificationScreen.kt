package com.example.matulemain.presentation.otpVerification

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.matulemain.R
import com.example.matulemain.presentation.signIn.DialogWindow
import com.example.matulemain.ui.theme.accent
import com.example.matulemain.ui.theme.hint
import com.example.matulemain.ui.theme.mainColor
import kotlinx.coroutines.delay

@Composable
fun OtpVerificationScreen() {

    //values
    var otpOne by remember { mutableStateOf("") }
    var otpBorder by remember { mutableStateOf(false) }

    var otpTwo by remember { mutableStateOf("") }

    var otpThree by remember { mutableStateOf("") }

    var otpFour by remember { mutableStateOf("") }

    var otpFive by remember { mutableStateOf("") }

    var otpSix by remember { mutableStateOf("") }


    //boolean

    var isShow by remember { mutableStateOf(false) }
    var hider by remember { mutableStateOf(false) }
    var timer by remember { mutableStateOf(30) }
    var startTimer by remember { mutableStateOf(false) }

    LaunchedEffect(startTimer) {
        while(timer > 0){
            delay(1000)
            timer--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

    ) {
        Spacer(Modifier.height(100.dp))
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.White)
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isShow) {
                DialogWindow("Ошибка", "OTP некорректно введен")
            }

            Text("OTP Проверка", fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = hint
            )
            Spacer(Modifier.height(16.dp))
            Text("OTP Код", fontSize = 21.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(20.dp))
            Row {
                TextField(
                    value = otpOne,
                    onValueChange = {
                        otpOne = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                )
                Spacer(Modifier.width(12.dp))
                TextField(
                    value = otpTwo,
                    onValueChange = {
                        otpTwo = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )
                Spacer(Modifier.width(12.dp))
                TextField(
                    value = otpThree,
                    onValueChange = {
                        otpThree = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true

                )
                Spacer(Modifier.width(12.dp))
                TextField(
                    value = otpFour,
                    onValueChange = {
                        otpFour = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true

                )
                Spacer(Modifier.width(12.dp))
                TextField(
                    value = otpFive,
                    onValueChange = {
                        otpFive = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true

                )
                Spacer(Modifier.width(12.dp))
                TextField(
                    value = otpSix,
                    onValueChange = {
                        otpSix = it
                    },
                    modifier = Modifier
                        .border(1.dp, color = if(otpBorder) Color.Red else mainColor, shape = RoundedCornerShape(12.dp))
                        .size(46.dp, 99.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = mainColor,
                        unfocusedContainerColor = mainColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true

                )
            }
        }
        Box (
            Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        ){
            Text(text = "Отправить заново", color = hint, fontSize = 12.sp)
            Text("00:${timer}", Modifier.fillMaxWidth(), textAlign = TextAlign.End,  fontSize = 12.sp, color = hint)
        }
        Spacer(Modifier.height(20.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = accent,

                ),
            enabled = if (
                otpOne.isNotEmpty() &&
                otpTwo.isNotEmpty() &&
                otpThree.isNotEmpty() &&
                otpFour.isNotEmpty() &&
                otpFive.isNotEmpty() &&
                otpSix.isNotEmpty()
            ) true else false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(50.dp),
            onClick = {

            },
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Отправить")
        }

    }
}



@Preview
@Composable
private fun OtpVerPreview() {
    OtpVerificationScreen()
}