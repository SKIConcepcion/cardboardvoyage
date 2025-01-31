package com.example.cardboardvoyage.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cardboardvoyage.R
import com.example.cardboardvoyage.ui.theme.OutlineColor
import com.example.cardboardvoyage.ui.theme.Purple40
import com.example.cardboardvoyage.ui.theme.heartColor
import com.example.cardboardvoyage.ui.theme.manaColor
import com.example.cardboardvoyage.ui.theme.papercuts


@Composable
fun BattleScreen(navController: NavHostController) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels

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

            Column (
                modifier = Modifier.padding(top = 45.dp),
                verticalArrangement = Arrangement.spacedBy((-30).dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                EnemyName("Training Dummy")
                EnemyHealthBar(width, 40, 100)
            }

            Field()

            Column (
                modifier = Modifier.padding(top = 45.dp),
                verticalArrangement = Arrangement.spacedBy((-20).dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                PlayerHealthBar(width, 50, 100)
                PlayerManaBar(width, 90, 100)
            }

            ActionTab()
        }
    }
}


@Composable
fun ColumnScope.EnemyHealthBar(
    width: Int = 412,
    value: Int = 50,
    maxValue: Int = 100,
) {
    Box(
        modifier = Modifier
            .width(width.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(30.dp)
                .width(width.dp)
                .drawBehind {
                    backgroundIndicator(width, value, maxValue, heartColor)
                    foregroundIndicator(width)
                }
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.heart).build(),
            contentDescription = "icon",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(100.dp)
                .width(100.dp)
        )

        Text(
            text = value.toString(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 15.dp, start = 36.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = papercuts,
            color = OutlineColor
        )
    }
}

fun DrawScope.backgroundIndicator(width: Int, value: Int, maxValue: Int, color: Color) {
    val health = (width-250f) * value/maxValue
    drawRoundRect(
        color = color,
        topLeft = Offset(190f, 20f),
        size = Size(health, 70f),
        cornerRadius = CornerRadius(20f, 20f)
    )
}

fun DrawScope.foregroundIndicator(width: Int) {
    drawRoundRect(
        color = OutlineColor,  // Make the fill color transparent
        topLeft = Offset(190f, 20f),
        size = Size(width-250f, 70f),
        style = Stroke(width = 12f),
        cornerRadius = CornerRadius(20f, 20f)
    )
}


@Composable
fun ColumnScope.EnemyName(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        fontFamily = papercuts,
        color = OutlineColor
    )
}

@Composable
fun ColumnScope.Field(
    text: String = "Field"
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically, // Vertically center the images
        modifier = Modifier.fillMaxWidth().weight(5f) // Ensure the row fills the width of the parent
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.guard).build(),
            contentDescription = "icon",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
        )


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.training_dummy).build(),
            contentDescription = "icon",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .scale(scaleX = -1f, scaleY = 1f)
        )
    }
}

@Composable
fun ColumnScope.PlayerHealthBar(
    width: Int = 412,
    value: Int = 50,
    maxValue: Int = 100,
) {
    Box(
        modifier = Modifier
            .width(width.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(30.dp)
                .width(width.dp)
                .drawBehind {
                    backgroundIndicator(width, value, maxValue, heartColor)
                    foregroundIndicator(width)
                }
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.heart).build(),
            contentDescription = "icon",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(100.dp)
                .width(100.dp)
        )

        Text(
            text = value.toString(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 15.dp, start = 36.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = papercuts,
            color = OutlineColor
        )
    }
}

@Composable
fun ColumnScope.PlayerManaBar(
    width: Int = 412,
    value: Int = 50,
    maxValue: Int = 100,
) {
    Box(
        modifier = Modifier
            .width(width.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(30.dp)
                .width(width.dp)
                .drawBehind {
                    backgroundIndicator(width, value, maxValue, manaColor)
                    foregroundIndicator(width)
                }
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.mana).build(),
            contentDescription = "icon",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(100.dp)
                .width(100.dp)
        )

        Text(
            text = value.toString(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 15.dp, start = 36.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = papercuts,
            color = OutlineColor
        )
    }
}


@Composable
fun ColumnScope.ActionTab() {
    Column(modifier = Modifier.weight(8f)) {

        // First Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // First square
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .drawBehind { weaponBox() }
            )

            // Second square
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .drawBehind { weaponBox() }
            )
        }

        // Second Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Third square
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .drawBehind { weaponBox() }
            )

            // Fourth square
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .drawBehind { weaponBox() }
            )
        }
    }
}

fun DrawScope.weaponBox() {
    drawRoundRect(
        color = OutlineColor,
        topLeft = Offset(50f, 50f),
        size = Size(450f, 300f),
        cornerRadius = CornerRadius(20f, 20f),
        style = Stroke(15f)
    )
}




@Composable
@Preview(showBackground = true)
fun HealthBarPreview() {
    val navController = rememberNavController()
    BattleScreen(navController)
}









