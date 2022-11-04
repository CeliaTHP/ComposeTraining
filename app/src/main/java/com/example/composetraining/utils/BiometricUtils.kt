package com.example.composetraining.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.provider.Settings
import android.telephony.SubscriptionManager.from
import android.transition.TransitionInflater.from
import android.util.Log
import android.view.LayoutInflater.from
import androidx.biometric.BiometricManager.from
import androidx.core.app.TaskStackBuilder.from
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.from
import com.example.composetraining.activities.BiometricsNavigationActivity
import com.example.composetraining.activities.MainActivity
import java.util.Date.from
import java.util.GregorianCalendar.from


object BiometricUtils {

    private const val TAG = "BiometricUtils"

    fun initBiometricPrompt(context: Context, shouldRequestCredentialCreation: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            Log.d(TAG, "is >= Android 30")


            var biometricPrompt =
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
                        Log.d(TAG, "onAuthenticationError")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        Log.d(TAG, "onAuthenticationSucceeded")

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.d(TAG, "onAuthenticationFailed")

                    }
                })


            //determine how the user authenticated
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

                    shouldRequestCredentialCreation()
                    //moved to callback
                    //startActivityForResult(enrollIntent, BiometricsNavigationActivity.REQUEST_CODE)
                }
            }


        }


    }


}


