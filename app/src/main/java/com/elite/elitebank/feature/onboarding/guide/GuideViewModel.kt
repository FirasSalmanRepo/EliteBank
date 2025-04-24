package com.elite.elitebank.feature.onboarding.guide

import com.elite.elitebank.core.BaseViewModel
import com.elite.elitebank.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor() : BaseViewModel<GuideEvent>() {

    override fun onEvent(event: GuideEvent) {
        when (event) {
            GuideEvent.OnGetStartedClicked -> navigationManager.navigateClearingStack(NavRoute.HOME.ROOT)

            GuideEvent.OnSkipClicked -> navigationManager.navigate(NavRoute.AUTH.ROOT)
        }
    }
}