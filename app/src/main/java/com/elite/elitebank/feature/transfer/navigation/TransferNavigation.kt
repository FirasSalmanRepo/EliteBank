package com.elite.elitebank.feature.transfer.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.elite.elitebank.core.baseComposable
import com.elite.elitebank.feature.transfer.TransferScreen
import com.elite.elitebank.feature.transfer.TransferViewModel
import com.elite.elitebank.navigation.NavRoute


fun NavGraphBuilder.transferBankNavGraph() = navigation(
    route = NavRoute.TransferBANK.ROOT,
    startDestination = NavRoute.TransferBANK.MAIN
) {
    baseComposable(NavRoute.TransferBANK.MAIN) {
        val viewModel: TransferViewModel = hiltViewModel()
        TransferScreen (state = viewModel.state, onEvent = viewModel::onEvent)
    }
}