package com.elite.elitebank.feature.transfer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransferViewModel :ViewModel() {
    var state by mutableStateOf(TransferState())
        private set

    fun onEvent(event: TransferEvent) {

    }
}