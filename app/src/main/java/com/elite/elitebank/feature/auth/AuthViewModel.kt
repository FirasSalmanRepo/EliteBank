package com.elite.elitebank.feature.auth


import androidx.lifecycle.viewModelScope
import com.elite.elitebank.core.BaseViewModel
import com.elite.elitebank.core.EmailValidationError
import com.elite.elitebank.core.PasswordValidationError
import com.elite.elitebank.core.UIText
import com.elite.elitebank.core.errorOrNull
import com.elite.elitebank.core.validators.ISingleStringValidator
import com.elite.elitebank.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AuthViewModel @Inject constructor(
    @Named("email") private val emailValidator: ISingleStringValidator,
    @Named("password") private val passwordValidator: ISingleStringValidator
) : BaseViewModel<LoginEvent>() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState>
        get() = _state.map {

            val emailError: EmailValidationError? =
                (emailValidator(it.enteredEmailId).errorOrNull as? EmailValidationError)

            val uiEmailError: UIText? = when (emailError) {
                EmailValidationError.INCORRECT -> UIText.DynamicString("Please enter a valid email!")
                else -> null
            }

            val passwordError: PasswordValidationError? =
                (passwordValidator(it.enteredPassword).errorOrNull as? PasswordValidationError)

            val uiPasswordError: UIText? = when (passwordError) {
                PasswordValidationError.TOO_SHORT -> UIText.DynamicString("Password too short!")
                PasswordValidationError.TOO_SIMPLE -> UIText.DynamicString("Password too simple!")
                else -> null
            }

            val shouldEnableLoginButton: Boolean = uiEmailError == null
                    && uiPasswordError == null
                    && it.enteredEmailId.isNotBlank()
                    && it.enteredPassword.isNotBlank()
                    && it.enteredName.isNotBlank()

            it.copy(
                isLoginButtonEnabled = shouldEnableLoginButton,
                emailIdError = uiEmailError,
                passwordError = uiPasswordError
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LoginScreenState())

    override fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnNameChanged -> {
                _state.update {
                    it.copy(
                        enteredName = event.newName
                    )
                }
            }

            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        enteredEmailId = event.newEmailId
                    )
                }
            }

            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        enteredPassword = event.newPassword
                    )
                }
            }

            LoginEvent.OnLoginClicked -> {
                // Just simulate login - no persistence
                val name = _state.value.enteredName.ifBlank { "Test User" }
                val email = _state.value.enteredEmailId
                    .lowercase(Locale.getDefault())
                    .ifBlank { "test@local.dev" }

                // You can pass data via nav args or skip for now
                println("User logged in locally: name = $name, email = $email")

                // Navigate to home screen
                navigationManager.navigateClearingStack(NavRoute.HOME.ROOT)
            }
        }
    }
}
