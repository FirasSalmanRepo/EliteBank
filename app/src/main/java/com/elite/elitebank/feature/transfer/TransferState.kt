package com.elite.elitebank.feature.transfer

data class TransferState(
    val isTransferButtonEnabled: Boolean = false,
    val accountNumberFrom: String = "",
    val accountNumberTo: String = "",
    val amount: String = "",
    val currencyFrom: String = "USD",
    val currencyTo: String = "USD",
    val branchCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
