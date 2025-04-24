package com.elite.elitebank.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.Paddings
import com.elite.elitebank.ui.theme.component.buttons.ElitePrimaryButton
import com.elite.elitebank.ui.theme.component.images.EliteGifImage
import com.elite.elitebank.ui.theme.component.inputs.ElitePasswordField
import com.elite.elitebank.ui.theme.component.inputs.EliteTextField
import com.elite.elitebank.ui.theme.component.scaffold.EliteScaffold
import com.elite.elitebank.ui.theme.headline1
import com.elite.elitebank.ui.theme.regular
import com.elite.elitebank.R.drawable as UIDrawables

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onEvent: (LoginEvent) -> Unit
) = LoginScreenContent(state = state, onEvent = onEvent)

@Composable
private fun LoginScreenContent(
    state: LoginScreenState,
    onEvent: (LoginEvent) -> Unit
) = EliteScaffold(
    modifier = Modifier.fillMaxSize(),
    optionalContainerColor = EliteColors.screenBackground.secondary
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.large)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EliteGifImage(modifier = Modifier.size(300.dp), data = UIDrawables.ic_elite_logo)
        Spacer(modifier = Modifier.height(Paddings.large))

        Text(
            text = "Login",
            style = headline1(),
            color = EliteColors.text.dark,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Paddings.small))

        Text(
            text = "Please login to get your local  Elite data.",
            style = regular(),
            color = EliteColors.text.light,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Paddings.large))

        EliteTextField(
            isSingleLine = true,
            text = state.enteredName,
            modifier = Modifier.fillMaxWidth(),
            optionalPlaceholderText = "Your name",
            optionalLeadingIcon = UIDrawables.ic_profile,
            optionalKeyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
        ) {
            onEvent(LoginEvent.OnNameChanged(it))
        }

        Spacer(modifier = Modifier.height(Paddings.medium))

        EliteTextField(
            isSingleLine = true,
            text = state.enteredEmailId,
            modifier = Modifier.fillMaxWidth(),
            optionalPlaceholderText = "Your email address",
            optionalLeadingIcon = UIDrawables.ic_mail,
            isError = state.emailIdError != null,
            optionalSupportingText = state.emailIdError?.asString(LocalContext.current),
            optionalKeyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        ) {
            onEvent(LoginEvent.OnEmailChanged(it))
        }

        Spacer(modifier = Modifier.height(Paddings.medium))

        ElitePasswordField(
            isSingleLine = true,
            text = state.enteredPassword,
            modifier = Modifier.fillMaxWidth(),
            optionalPlaceholderText = "Your password",
            optionalLeadingIcon = UIDrawables.ic_key,
            isError = state.passwordError != null,
            optionalSupportingText = state.passwordError?.asString(LocalContext.current),
            optionalKeyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            optionalKeyboardActions = KeyboardActions(
                onGo = {
                    if (state.isLoginButtonEnabled) {
                        onEvent(LoginEvent.OnLoginClicked)
                    }
                }
            )
        ) {
            onEvent(LoginEvent.OnPasswordChanged(it))
        }

        Spacer(modifier = Modifier.height(Paddings.large))

        Spacer(modifier = Modifier.weight(1f))

        ElitePrimaryButton(
            text = "Login",
            isEnable = state.isLoginButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            onEvent(LoginEvent.OnLoginClicked)
        }

        Spacer(modifier = Modifier.height(Paddings.large))
    }
}


@Preview
@Composable
private fun LoginScreenInitPreview() = LoginScreenContent(
    state = LoginScreenState()
) {}

@Preview
@Composable
private fun LoginScreenDataPreview() = LoginScreenContent(
    state = LoginScreenState(
        enteredEmailId = "firassalman.93@gmail.com",
        enteredPassword = "pass@word",
        isLoginButtonEnabled = true
    )
) {}
