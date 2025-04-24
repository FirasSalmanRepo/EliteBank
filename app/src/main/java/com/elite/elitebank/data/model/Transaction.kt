package com.elite.elitebank.data.model

data class Transaction(
    val imageUrl: String,
    val name: String,
    val date: String,
    val amount: String,
    val isDebit: Boolean
)