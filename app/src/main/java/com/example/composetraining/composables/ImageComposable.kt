package com.example.composetraining.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.composetraining.R

object ImageComposable {

    @Composable
    fun SmileAndPayLogo() {
        val image: Painter = painterResource(id = R.drawable.snp_logo)
        Image(painter = image, contentDescription = "Smile and pay logo")
    }
}