package com.example.composetraining.navigation

sealed class Screen
    (val route: String) {

    object MainScreen : Screen("MainScreen")
    object SecondScreen : Screen("SecondScreen")
    object ThirdScreen : Screen("ThirdScreen")
}