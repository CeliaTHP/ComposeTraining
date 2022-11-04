package com.example.composetraining.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composetraining.composables.BottomButtonComposable
import com.example.composetraining.composables.ImageComposable

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ImageComposable.SmileAndPayLogo()
            }
            BottomButtonComposable.SingleButtonRow(
                buttonText = "Settings",
                backgroundColor = Color.Green
            ) {
                Log.d(TAG, "onClick")
                val intent = Intent(this@MainActivity, BiometricsNavigationActivity::class.java)
                startActivity(intent)
            }

        }


    }
}
