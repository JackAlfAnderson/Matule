package com.example.matulemain.presentation.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulemain.R
import com.example.matulemain.ui.theme.accent

@Composable
fun OnBoardingScreen() {

    val pages = listOf(
        OnBoardItem(
            image = painterResource(R.drawable.first_sneaker),
            "",
            ""
        ),
        OnBoardItem(
            painterResource(R.drawable.secondsneaker),
            "Начнем путшествие",
            "Умная, великолепная и модная коллекция Изучите сейчас"
        ),
        OnBoardItem(
            image = painterResource(R.drawable.thirdsneaker),
            "У вас есть сила, чтобы",
            "В вашей комнате много красивых и привлекательных растений"
        )
    )

    var pagerState = rememberPagerState { pages.size }

    HorizontalPager(pagerState) { page ->
        Box (Modifier.fillMaxSize()){
            if(page == 0){
                OnBoardFirstScreen()
            } else {
                OnBoardOtherScreen(pages[page])
            }
        }
    }

}

@Composable
fun OnBoardOtherScreen(onBoardItem: OnBoardItem) {
    Column(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(Color(0xFF48B2E7), Color(0xFF2B6B8B))))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = onBoardItem.image, null,
            Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Text(
            onBoardItem.title,
            fontSize = 34.sp,
            modifier = Modifier.padding(horizontal = 70.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))
        Text(
            onBoardItem.subtitle,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 70.dp),
            color = Color(0xffD8D8D8),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(135.dp))
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            ),
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 36.dp)
                .height(50.dp),
            shape = RoundedCornerShape(13.dp),
        ) {
            Text("Далее", color = Color.Black)
        }
    }


}

@Composable
fun OnBoardFirstScreen() {
    Column {
        Column(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Color(0xFF48B2E7), Color(0xFF2B6B8B))))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Добро пожаловать".uppercase(),
                fontSize = 30.sp,
                modifier = Modifier.padding(horizontal = 70.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(R.drawable.first_sneaker), null,
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }
    }

}

@Preview
@Composable
private fun OnBoardScreenPreview() {
    OnBoardOtherScreen(
        OnBoardItem(
            painterResource(R.drawable.secondsneaker),
            "Начнем путшествие",
            "Умная, великолепная и модная коллекция Изучите сейчас"
        )
    )
}

data class OnBoardItem(
    val image: Painter,
    val title: String,
    val subtitle: String
)