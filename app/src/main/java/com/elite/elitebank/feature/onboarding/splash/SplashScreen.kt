package com.elite.elitebank.feature.onboarding.splash

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elite.elitebank.R
import com.elite.elitebank.security.CheckSecurityAndShowUI

import com.elite.elitebank.security.SecurityIssue
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.component.images.EliteGifImage
import com.elite.elitebank.ui.theme.component.images.EliteIllustration
import com.elite.elitebank.R.drawable as UIDrawables

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state: SplashScreenState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context as? Activity

    val isMultiWindow = remember(activity) {
        activity?.let { multiWindowModeDetector(it) } ?: false
    }




//    if (isMultiWindow) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(EliteColors.screenBackground.secondary),
            contentAlignment = Alignment.Center
        ) {
            EliteIllustration(
                illustration = R.drawable.splash_bg,
                modifier = Modifier.fillMaxSize(),
                optionalContentScale = ContentScale.FillBounds
            )
            EliteGifImage(
                modifier = Modifier.size(250.dp),
                data = UIDrawables.ic_elite_logo
            )
        }
//    } else {
//        CheckSecurityAndShowUI("Your device is in multi-window mode.For your security, the app will now close.")
//    }

    when (state.securityIssue) {
        is SecurityIssue.DebuggerAttached -> {
            CheckSecurityAndShowUI("Debug mode are enabled on this device.For your security, the app will now close.")
        }

        is SecurityIssue.DeveloperOptionsEnabled -> {
            CheckSecurityAndShowUI("Developer Options are enabled on this device.For your security, the app will now close..")
        }

        is SecurityIssue.DeviceRooted -> {
            CheckSecurityAndShowUI("Your device is rooted.For your security, the app will now close.")
        }

        is SecurityIssue.DeviceEmulator -> {
            CheckSecurityAndShowUI("Your device is running in an emulator.For your security, the app will now close.")
        }

        is SecurityIssue.LoadFromGooglePlay -> {
            CheckSecurityAndShowUI("Your app is not installed from Google Play.For your security, the app will now close.")
        }

        is SecurityIssue.CameraDetector -> {
            CheckSecurityAndShowUI("another device is using a camera.For your security, the app will now close.")
        }

        is SecurityIssue.OverLayDetector-> {
            CheckSecurityAndShowUI("another device is using a overlay.For your security, the app will now close.")
        }

        is SecurityIssue.None -> {
            // mo message
        }

        else -> {}
    }
}




// this function used for prevent multi window
fun multiWindowModeDetector(activity: Activity): Boolean =
    activity.isInMultiWindowMode

@Preview
@Composable
private fun SplashScreenPreview() = SplashScreen()