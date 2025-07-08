package com.elite.elitebank.feature.onboarding.splash

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.elite.elitebank.core.BaseViewModel
import com.elite.elitebank.feature.onboarding.splash.SplashEvent.OnConfirmLogoutPopupAction
import com.elite.elitebank.navigation.NavRoute
import com.elite.elitebank.security.EmulatorDetection
import com.elite.elitebank.security.SecurityIssue
import com.elite.elitebank.security.SecurityUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel<SplashEvent>() {

    private val _state = MutableStateFlow(SplashScreenState())
    val state: StateFlow<SplashScreenState> = _state.asStateFlow()

    init {
        runSecurityChecks()
    }

    private fun runSecurityChecks() = viewModelScope.launch {
        delay(5000) // ننتظر قليلًا قبل بدء باقي الفحوصات
        val issue = when {
            // قم بإلغاء التعليقات لتفعيل التحقق:
            SecurityUtils.isDebugEnabled(context) -> SecurityIssue.DebuggerAttached
            SecurityUtils.isDeveloperOptionsEnabled(context) -> SecurityIssue.DeveloperOptionsEnabled
            SecurityUtils.isRooted() -> SecurityIssue.DeviceRooted
            EmulatorDetection.isRunningInEmulator(context) -> SecurityIssue.DeviceEmulator
            !SecurityUtils.isAppInstallFromGooglePlay(context) -> SecurityIssue.LoadFromGooglePlay
            SecurityUtils.isCameraInUse() -> SecurityIssue.CameraDetector
            SecurityUtils.startOverlayDetection() -> SecurityIssue.OverLayDetector
            else -> SecurityIssue.None
        }

        // فقط إذا لم يكن هناك كشف للتراكب مسبقًا
        if (_state.value.securityIssue is SecurityIssue.None) {
            updateSecurityIssue(issue)
        }

        if (issue is SecurityIssue.None) {
            navigationManager.navigateClearingStack(NavRoute.ONBOARDING.GUIDE)
        }
    }

    private fun updateSecurityIssue(issue: SecurityIssue) {
        _state.update { it.copy(securityIssue = issue) }
    }

    override fun onEvent(event: SplashEvent) {
        super.onEvent(event)
        when (event) {
            is OnConfirmLogoutPopupAction -> {
                _state.update {
                    it.copy(isConfirmLogoutPopupVisible = false)
                }
            }
        }
    }
}

