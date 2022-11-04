package com.example.composetraining.activities

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetraining.navigation.BiometricsNavigation

class BiometricsNavigationActivity : ComponentActivity() {

    companion object {
        private const val TAG = "BioNavActivity"
        private const val REQUEST_CODE = 0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            BiometricsNavigation {
                Log.d(TAG, "onCredentialRequestRequired")
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
        BiometricUtils.initBiometricPrompt(this) {
            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
            }
            startActivityForResult(enrollIntent, REQUEST_CODE)
        }


         */

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