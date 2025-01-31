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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cardboardvoyage.R
import com.example.cardboardvoyage.StoryScreen
import com.example.cardboardvoyage.ui.theme.OutlineColor
import com.example.cardboardvoyage.ui.theme.papercuts
import kotlinx.coroutines.delay


@Composable
fun StoryScreen(navController: NavHostController, args: StoryScreen) {

    val storyTexts = mapOf(
        1 to "Once upon a time in the Cardboard Land, there was a kingdom — the Cardboard Kingdom!",
        2 to "It is the home of countless Cardboard Citizens and Cardboard Creatures.",
        3 to "But right now, they are in despair as an invisible threat looms over the outskirts of the land, slowly marching toward the kingdom's demise!",
        4 to "Fortunately, the Cardboard guards managed to catch one of the creatures...",
        5 to "Here's one inside of a cage — it's invisible, but it is definitely somewhere there inside.",
        6 to "The kingdom believes that the only way to defeat these invisible threats is through a special flower — The Iridescence.",
        7 to "Its luminous color is believed to be the key to making the threats visible, so the kingdom can finally fight back.",
        8 to "Legend says that it is found in a mystical realm where the Princess is being held captive.",
        9 to "And this is where you arrive at the scene — your goal is to journey across the land and retrieve the flower...",
        10 to "And oh, you can also save the princess along the way, but it's optional.",
        11 to "But first, your skill has to be put to the test...",
        12 to "Against this dummy."
    )

    val textToDisplay = storyTexts[args.story] ?: "Welcome to the Cardboard Voyage!"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
            StoryScreenImage(args.story)

            TypewriterText(
                texts = listOf(
                    textToDisplay
                ),
            )

            NextButton(navController, (args.story?.plus(1)))
        }
    }
}


@Composable
fun ColumnScope.StoryScreenImage(image: Int?) {

    // Map of story IDs to texts
    val storyImage = mapOf(
        1 to R.drawable.kingdom,
        2 to R.drawable.kingdom,
        3 to R.drawable.iridescence,
        4 to R.drawable.cage,
        5 to R.drawable.cage,
        6 to R.drawable.iridescence,
        7 to R.drawable.iridescence,
        8 to R.drawable.iridescence,
        9 to R.drawable.iridescence,
        10 to R.drawable.iridescence,
        11 to R.drawable.iridescence,
        12 to R.drawable.iridescence
    )

    val imageToDisplay = storyImage[image]

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
        label = "scale"
    )

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageToDisplay).build(),
        contentDescription = "icon",
        modifier = Modifier.weight(4f).height(170.dp).width(400.dp).graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin.Center
        }
    )
}


@Composable
fun ColumnScope.TypewriterText(texts: List<String>) {
    var textToDisplay by remember { mutableStateOf("") }
    var isComplete by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = texts) {
        for (index in texts.indices) {
            texts[index].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[index].substring(0, charIndex + 1)
                delay(70)
            }
        }
        isComplete = true
    }

    Text(
        modifier = Modifier.weight(2f).width(300.dp),
        lineHeight = 36.sp,
        textAlign = TextAlign.Center,
        text = textToDisplay,
        fontSize = 28.sp,
        fontFamily = papercuts,
        color = OutlineColor
    )
}

@Composable
fun ColumnScope.NextButton(navController: NavHostController, next: Int?) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(R.drawable.next_button).build(),
        contentDescription = "icon",
        modifier = Modifier.weight(1f).height(20.dp).width(80.dp).clickable {
            navController.navigate(StoryScreen(next))
        }
    )
}





