package com.example.composetraining.utils

import android.content.Context
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.util.Log
import androidx.core.content.ContextCompat


object BiometricUtils {

    private const val TAG = "BiometricUtils"

    fun requestBiometrics(context: Context, onCredentialsCreationNeeded: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            //in Activity ?
            initBiometricsListener(context)

            Log.d(TAG, "is >= Android 30")

            val biometricManager = androidx.biometric.BiometricManager.from(context)
            when (biometricManager.canAuthenticate()) {
                androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS ->
                    Log.d(
                        TAG,
                        "App can authenticate using biometrics."
                    )
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    Log.e(
                        TAG,
                        "No biometric features available on this device."
                    )
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    Log.e(
                        TAG,
                        "Biometric features are currently unavailable."
                    )
                androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // Prompts the user to create credentials that your app accepts.
                    Log.d(
                        TAG,
                        "User should create his credentials"
                    )

                    onCredentialsCreationNeeded()
                    //moved to callback
                    //startActivityForResult(enrollIntent, BiometricsNavigationActivity.REQUEST_CODE)
                }
            }

        } else {
            Log.d(TAG, "is < Android 30")

        }


    }

    fun initBiometricsListener(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {


            val biometricPrompt =
                BiometricPrompt.Builder(context)
                    .setTitle("Biometric login title")
                    .setSubtitle("Biometric login subtitle rezijroezr")
                    .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                    .build()


            val executor = ContextCompat.getMainExecutor(context)

            biometricPrompt.authenticate(
                CancellationSignal(), executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence?
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        //Called when credential creation is required
                        //Force toggle to turn off if enabled on click
                        Log.d(TAG, "onAuthenticationError")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        //Force toggle to turn on if onclick disabled
                        Log.d(TAG, "onAuthenticationSucceeded")

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.d(TAG, "onAuthenticationFailed")
                        //Count fails to request twice
                        //Force toggle to turn off if enabled on click


                    }
                })


        } else {
            Log.d(TAG, "is < Android R")
        }


    }
}


