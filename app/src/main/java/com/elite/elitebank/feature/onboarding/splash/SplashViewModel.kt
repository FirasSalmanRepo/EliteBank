package com.elite.elitebank.feature.onboarding.splash

import androidx.lifecycle.viewModelScope
import com.elite.elitebank.core.BaseScreenEvent
import com.elite.elitebank.core.BaseViewModel
import com.elite.elitebank.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : BaseViewModel<BaseScreenEvent>() {

    init {
        navigateNext()
    }

    private fun navigateNext() = viewModelScope.launch {
        delay(1000)
        val nextRoute = NavRoute.ONBOARDING.GUIDE
        navigationManager.navigateClearingStack(route = nextRoute)
    }
}
