package com.example.peyaecommerce.view.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.compose.*
import com.example.peyaecommerce.R
import com.example.peyaecommerce.model.database.populateDatabaseIfEmpty

@Composable
fun SplashScreen(
    onAnimationFinished: () -> Unit = {}
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation_food)
    )
    val animatable = rememberLottieAnimatable()
    val context = LocalContext.current

    LaunchedEffect(composition) {
        if (composition != null) {
            populateDatabaseIfEmpty(context)

            animatable.animate(
                composition = composition,
                iterations = 1,
                speed = 1.5f
            )
            onAnimationFinished()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF4A0D22))
    ) {
        LottieAnimation(
            composition = composition,
            progress = { animatable.progress },
            modifier = Modifier.size(200.dp)
        )
    }
}

