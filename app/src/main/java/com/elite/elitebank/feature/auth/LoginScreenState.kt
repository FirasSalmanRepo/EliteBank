package com.elite.elitebank.feature.auth

import com.elite.elitebank.core.UIText

data class LoginScreenState(
    val enteredName: String = "",
    val enteredEmailId: String = "",
    val emailIdError: UIText? = null,
    val enteredPassword: String = "",
    val passwordError: UIText? = null,
    val isLoginButtonEnabled: Boolean = false
)
