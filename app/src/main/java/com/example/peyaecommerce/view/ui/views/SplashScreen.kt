package com.example.peyaecommerce.view.ui.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.compose.*
import com.example.peyaecommerce.R
import com.example.peyaecommerce.model.data.ProductDataBase
import com.example.peyaecommerce.model.data.populateDatabaseIfEmpty
import kotlinx.coroutines.flow.firstOrNull

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

