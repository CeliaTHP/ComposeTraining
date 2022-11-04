package com.example.composetraining.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetraining.R
import com.example.composetraining.composables.BottomButtonComposable
import com.example.composetraining.composables.ImageComposable
import com.example.composetraining.composables.SwitchComposable
import com.example.composetraining.utils.BiometricUtils


@Composable
fun BiometricsNavigation(onCredentialsCreationNeeded: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(route = Screen.SecondScreen.route) {
            SecondScreen(navController)
        }
        composable(route = Screen.ThirdScreen.route) {
            //Pass context ?
            ThirdScreen(navController, LocalContext.current, onCredentialsCreationNeeded)
        }

    }
}

@Composable
fun MainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageComposable.SmileAndPayLogo()
        Text("This is the main Screen", fontSize = 26.sp)
    }
    BottomButtonComposable.SingleButtonRow(
        LocalContext.current.getString(R.string.go),
        Color.Blue
    ) {
        navController.navigate(Screen.SecondScreen.route)
    }

}

@Composable
fun SecondScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageComposable.SmileAndPayLogo()
        Text("This is the second screen", fontSize = 20.sp)
    }
    BottomButtonComposable.DoubleButtonsRow(LocalContext.current.getString(R.string.cancel),
        LocalContext.current.getString(R.string.go_on), {
            navController.navigate(Screen.ThirdScreen.route)
        }, {
            navController.navigate(Screen.MainScreen.route)
        })

}

@Composable
fun ThirdScreen(
    navController: NavController,
    context: Context,
    onCredentialsCreationNeeded: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxSize(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SwitchComposable.SwitchWithText(name = "Biometric Fingerprint", onCheckedChange = {
            Log.d("ThirdScreen", "is now : $it")
            if (it) {
                BiometricUtils.initBiometricPrompt(context, onCredentialsCreationNeeded)
            }

        })
    }
    BottomButtonComposable.SingleButtonRow(
        LocalContext.current.getString(R.string.done),
        backgroundColor = Color.Magenta
    ) {
        navController.navigate(Screen.SecondScreen.route)

    }


}










