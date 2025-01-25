package com.example.cardboardvoyage.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cardboardvoyage.MainActivity
import com.example.cardboardvoyage.R
import com.example.cardboardvoyage.StoryScreen


@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centers the content of the Box both vertically and horizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.background).build(),
            contentDescription = "Background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Logo()
            Spacer(modifier = Modifier.height(24.dp))
            Column (verticalArrangement = Arrangement.spacedBy((-20).dp)){
                StartButton(navController)
                ExitButton(navController)
            }
        }
    }
}


@Composable
fun Logo() {

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
        label = "scale"
    )

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(R.drawable.logo).build(),
        contentDescription = "icon",
        modifier = Modifier.height(200.dp).width(400.dp).graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin.Center
        }
    )
}

@Composable
fun StartButton(navController: NavHostController) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(R.drawable.start_button).build(),
        contentDescription = "icon",
        modifier = Modifier.height(100.dp).width(400.dp).clickable { navController.navigate(StoryScreen(1)) }
    )
}

@Composable
fun ExitButton(navController: NavHostController) {

    val activity = MainActivity()

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(R.drawable.exit_button).build(),
        contentDescription = "icon",
        modifier = Modifier.height(100.dp).width(400.dp).clickable { activity.finish() }
    )
}


