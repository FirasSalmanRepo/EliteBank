package com.elite.elitebank.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.elite.elitebank.feature.auth.navigation.authNavGraph
import com.elite.elitebank.feature.home.navigation.homeNavGraph
import com.elite.elitebank.feature.menu.navigation.menuNavGraph
import com.elite.elitebank.feature.onboarding.navigation.onboardingNavGraph
import com.elite.elitebank.feature.transfer.navigation.transferBankNavGraph

@Composable
fun EliteAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) = NavHost(
    navController = navController,
    startDestination = NavRoute.ONBOARDING.ROOT,
    modifier = modifier,
    enterTransition = EliteNavTransitions.enterTransition,
    exitTransition = EliteNavTransitions.exitTransition,
    popEnterTransition = EliteNavTransitions.popEnterTransition,
    popExitTransition = EliteNavTransitions.popExitTransition
) {
    onboardingNavGraph()
    authNavGraph()
    homeNavGraph()
    menuNavGraph()
    transferBankNavGraph()
}
