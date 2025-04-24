package com.elite.elitebank.feature.onboarding.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.elite.elitebank.core.baseComposable
import com.elite.elitebank.feature.onboarding.guide.GuideScreen
import com.elite.elitebank.feature.onboarding.guide.GuideViewModel
import com.elite.elitebank.feature.onboarding.splash.SplashScreen
import com.elite.elitebank.feature.onboarding.splash.SplashViewModel
import com.elite.elitebank.navigation.NavRoute


fun NavGraphBuilder.onboardingNavGraph() = navigation(
    route = NavRoute.ONBOARDING.ROOT,
    startDestination = NavRoute.ONBOARDING.SPLASH
) {

    baseComposable(NavRoute.ONBOARDING.SPLASH) {
        val viewModel: SplashViewModel = hiltViewModel()
        SplashScreen()
    }

    baseComposable(NavRoute.ONBOARDING.GUIDE) {
        val viewModel: GuideViewModel = hiltViewModel()
        GuideScreen(onEvent = viewModel::onEvent)
    }
}
