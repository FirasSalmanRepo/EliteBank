package com.elite.elitebank.feature.onboarding.splash

import com.elite.elitebank.security.SecurityIssue

data class SplashScreenState(
    val securityIssue: SecurityIssue = SecurityIssue.None,
    val isConfirmLogoutPopupVisible: Boolean = false
)
