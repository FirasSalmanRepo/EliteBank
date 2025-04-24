package com.elite.elitebank.feature.transfer

sealed class TransferEvent {

    data object OnTransferClicked : TransferEvent()
    data class EnterAmount(val value: String) : TransferEvent()
}
