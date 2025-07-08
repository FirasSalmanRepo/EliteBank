package com.elite.elitebank.feature.onboarding.splash

sealed class SplashEvent {
    data class OnConfirmLogoutPopupAction(val isConfirm: Boolean) : SplashEvent()
}

