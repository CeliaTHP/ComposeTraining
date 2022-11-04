package com.example.composetraining.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object BottomButtonComposable {

    @Composable
    fun SingleButtonRow(
        buttonText: String,
        backgroundColor: Color,
        onButtonClick: () -> Unit
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier =
                Modifier.padding(10.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = backgroundColor),
                onClick = {
                    onButtonClick()
                }) {
                Text(buttonText, color = Color.White)
            }
        }
    }


    @Composable
    fun DoubleButtonsRow(
        negativeButtonText: String,
        positiveButtonText: String,
        onPositiveButtonClick: () -> Unit,
        onNegativeButtonClick: () -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier =
                Modifier.padding(10.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
                onClick = {
                    onNegativeButtonClick()
                },

                ) { Text(negativeButtonText, color = Color.White) }
            Button(
                modifier =
                Modifier.padding(10.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green),
                onClick = {
                    onPositiveButtonClick()
                }) { Text(positiveButtonText, color = Color.White) }
        }
    }
}