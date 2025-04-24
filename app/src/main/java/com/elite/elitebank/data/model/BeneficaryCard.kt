package com.elite.elitebank.data.model

data class BankCard(
    val balance: String,
    val cardNumber: String,
    val holderName: String,
    val expiryDate: String,
    val cvv: String
)