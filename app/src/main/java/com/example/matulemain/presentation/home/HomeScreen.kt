package com.example.matulemain.presentation.home

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulemain.R
import com.example.matulemain.ui.theme.back

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .background(back)
            .fillMaxSize()
    ) {
        Column(
            Modifier.padding(20.dp).fillMaxSize()
        ) {
            Row {

                Icon(
                    painterResource(R.drawable.hamburger),
                    null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Spacer(Modifier.width(30.dp))
                Spacer(Modifier.width(30.dp))
                Spacer(Modifier.width(30.dp))

                Text("Главная", fontSize = 32.sp, )
                Spacer(Modifier.width(30.dp))
                Spacer(Modifier.width(10.dp))
                Spacer(Modifier.width(30.dp))
                Icon(painter = painterResource(R.drawable.bagnotifiedicon), null, tint = Color.Unspecified)
            }

        }


    }



}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}