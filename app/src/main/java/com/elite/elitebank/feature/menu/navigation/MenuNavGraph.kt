package com.elite.elitebank.feature.menu.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.elite.elitebank.core.baseComposable
import com.elite.elitebank.feature.menu.MenuListScreen
import com.elite.elitebank.feature.menu.MenuListScreenState
import com.elite.elitebank.feature.menu.MenuListViewModel
import com.elite.elitebank.navigation.NavRoute

fun NavGraphBuilder.menuNavGraph() = navigation(
    route = NavRoute.Menu.ROOT,
    startDestination = NavRoute.Menu.LIST
) {
    baseComposable(NavRoute.Menu.LIST) {
        val viewModel: MenuListViewModel = hiltViewModel()
        val state:MenuListScreenState by viewModel.state.collectAsStateWithLifecycle()
        MenuListScreen(state = state, onEvent = viewModel::onEvent)
    }
}
