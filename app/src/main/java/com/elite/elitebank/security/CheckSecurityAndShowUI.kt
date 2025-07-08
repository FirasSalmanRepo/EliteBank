package com.elite.elitebank.security

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.elite.elitebank.feature.onboarding.splash.SplashEvent
import com.elite.elitebank.ui.theme.component.dialogs.EliteAlertDialog

@Preview
@Composable
fun CheckSecurityAndShowUI(
    message: String
) {
    val context = LocalContext.current
    Dialog(message, onEvent = {
        // Exit the app safely
        (context as? ComponentActivity)?.finishAffinity()
    })
}

@Composable
fun Dialog(message: String, onEvent: (SplashEvent) -> Unit) {
    EliteAlertDialog(
        title = "⚠\uFE0F Warning ",
        optionalSubtitle = message,
        primaryButtonTextToActionPair = "Exit" to {
            onEvent(SplashEvent.OnConfirmLogoutPopupAction(true))
        }
    ) { }
}