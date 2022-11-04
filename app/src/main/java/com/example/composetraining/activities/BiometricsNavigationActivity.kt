package com.example.composetraining.activities

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.hardware.biometrics.BiometricPrompt
import android.hardware.biometrics.BiometricManager as HardwareBiometricManager

import androidx.biometric.BiometricManager as BiometricManager

import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.composetraining.navigation.BiometricsNavigation
import com.example.composetraining.utils.BiometricUtils
import java.util.concurrent.Executors

class BiometricsNavigationActivity : ComponentActivity() {

    companion object {
        private const val TAG = "BioNavActivity"
        private const val REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BiometricsNavigation()
        }

        BiometricUtils.initBiometricPrompt(this) {
            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
            }
            startActivityForResult(enrollIntent, REQUEST_CODE)
        }


    }


    /*
    val biometricPrompt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        // >= Pie

        val executor = Executors.newSingleThreadExecutor()


        val biometricPrompt = BiometricPrompt(this,executor,object :)


       val biometricPrompt = BiometricPrompt.Builder(this).build().authenticate()
    } else {
        TODO("VERSION.SDK_INT < P")
    }

    public val biometricManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        BiometricManager().canAuthenticate(0)
    } else {
        TODO("VERSION.SDK_INT < R")
    }

     */

}