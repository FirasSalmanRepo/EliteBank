package com.elite.elitebank.feature.home

sealed class HomeEvent {

    data object OnPulledToRefresh : HomeEvent()
    object TransferClicked : HomeEvent()
    object PaymentsClicked : HomeEvent()
    object AddCardClicked : HomeEvent()
    object MenuClicked : HomeEvent()

}
