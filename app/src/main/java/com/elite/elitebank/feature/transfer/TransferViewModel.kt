package com.elite.elitebank.feature.transfer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elite.elitebank.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferViewModel  @Inject constructor(

) : BaseViewModel<TransferEvent>() {
    var state by mutableStateOf(TransferState())
        private set

    override fun onEvent(event: TransferEvent) {
        super.onEvent(event)

    }
}