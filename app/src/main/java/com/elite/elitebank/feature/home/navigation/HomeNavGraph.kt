package com.elite.elitebank.feature.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.elite.elitebank.core.baseComposable
import com.elite.elitebank.feature.home.HomeScreen
import com.elite.elitebank.feature.home.HomeScreenState
import com.elite.elitebank.feature.home.HomeViewModel
import com.elite.elitebank.navigation.NavRoute


fun NavGraphBuilder.homeNavGraph() = navigation(
    route = NavRoute.HOME.ROOT,
    startDestination = NavRoute.HOME.HomeDIR
) {

    baseComposable(NavRoute.HOME.HomeDIR) {
        val viewModel: HomeViewModel = hiltViewModel()
        val state: HomeScreenState by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
