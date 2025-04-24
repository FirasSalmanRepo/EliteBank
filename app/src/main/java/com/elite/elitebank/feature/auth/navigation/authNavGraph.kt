package com.elite.elitebank.feature.auth.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.elite.elitebank.core.baseComposable
import com.elite.elitebank.feature.auth.AuthViewModel
import com.elite.elitebank.feature.auth.LoginScreen
import com.elite.elitebank.feature.auth.LoginScreenState
import com.elite.elitebank.navigation.NavRoute

fun NavGraphBuilder.authNavGraph() = navigation(
    route = NavRoute.AUTH.ROOT,
    startDestination = NavRoute.AUTH.LOGIN
) {
    baseComposable(NavRoute.AUTH.LOGIN) {
        val viewModel: AuthViewModel = hiltViewModel()
        val state: LoginScreenState by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(state = state, onEvent = viewModel::onEvent)
    }
}