package com.elite.elitebank.feature.auth

sealed class LoginEvent {

    data class OnNameChanged(val newName: String) : LoginEvent()

    data class OnEmailChanged(val newEmailId: String) : LoginEvent()

    data class OnPasswordChanged(val newPassword: String) : LoginEvent()

    data object OnLoginClicked : LoginEvent()
}
