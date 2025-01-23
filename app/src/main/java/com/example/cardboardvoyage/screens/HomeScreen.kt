package com.example.cardboardvoyage.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cardboardvoyage.MainActivity
import com.example.cardboardvoyage.R
import com.example.cardboardvoyage.ui.theme.cuteDino
import com.example.cardboardvoyage.StoryScreen


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(R.drawable.home_screen).build(),
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.padding(top = 150.dp, start = 20.dp)
            ) {
                TitleText()
                StartButton(navController)
                ExitButton(navController)

            }
        }
    }
}


@Composable
fun TitleText() {

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")

    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFFFFB6C1),
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
        label = "color"
    )

    Column (
        verticalArrangement = Arrangement.spacedBy((-15).dp),
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        Text(
            text = "SEINA's",
            fontFamily = cuteDino,
            fontSize = 56.sp,
            color = animatedColor,
        )

        Text(
            text = "MOONFLOWER",
            fontFamily = cuteDino,
            fontSize = 40.sp,
            color = animatedColor,
        )
    }

}

@Composable
fun StartButton(navController: NavHostController) {
    Text(
        text = "START",
        fontFamily = cuteDino,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable { navController.navigate(StoryScreen(null)) }
    )
}

@Composable
fun ExitButton(navController: NavHostController) {

    val activity = MainActivity()

    Text(
        text = "EXIT",
        fontFamily = cuteDino,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable { activity.finish() }
            .padding(top = 15.dp)
    )
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

