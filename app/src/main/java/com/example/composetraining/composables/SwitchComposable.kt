package com.example.composetraining.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object SwitchComposable {


    @Composable
    fun SwitchWithText(name: String, onCheckedChange: (Boolean) -> Unit) {

        val checkedState = remember { mutableStateOf(true) }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = name)

            Switch(
                checked = checkedState.value,
                onCheckedChange =
                {
                    checkedState.value = it
                    onCheckedChange(it)
                },
            )
        }


    }

}