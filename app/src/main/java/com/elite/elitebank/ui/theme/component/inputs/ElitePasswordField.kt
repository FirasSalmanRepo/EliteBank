package com.elite.elitebank.ui.theme.component.inputs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.elite.elitebank.R

@Composable
fun ElitePasswordField(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    isError: Boolean = false,
    isSingleLine: Boolean = false,
    optionalSupportingText: String? = null,
    optionalPlaceholderText: String? = null,
    @DrawableRes optionalLeadingIcon: Int? = null,
    optionalKeyboardOptions: KeyboardOptions? = null,
    optionalKeyboardActions: KeyboardActions? = null,
    onChange: (String) -> Unit
) = EliteTextField(
    text = text,
    modifier = modifier,
    isEnabled = isEnabled,
    isReadOnly = isReadOnly,
    isPasswordField = true,
    isError = isError,
    isSingleLine = isSingleLine,
    optionalSupportingText = optionalSupportingText,
    optionalPlaceholderText = optionalPlaceholderText,
    optionalLeadingIcon = optionalLeadingIcon,
    optionalTrailingIcon = R.drawable.ic_eye,
    optionalKeyboardOptions = optionalKeyboardOptions,
    optionalKeyboardActions = optionalKeyboardActions,
    onChange = onChange
)

@Preview
@Composable
private fun ElitePasswordFieldEmptyPreview() = ElitePasswordField(
    text = "",
    optionalPlaceholderText = "Your password",
    modifier = Modifier.fillMaxWidth()
) {}

@Preview
@Composable
private fun ElitePasswordFieldWithPasswordPreview() = ElitePasswordField(
    text = "password",
    optionalPlaceholderText = "Your password here",
    modifier = Modifier.fillMaxWidth(),
    optionalLeadingIcon = R.drawable.ic_key
) {}

@Preview
@Composable
private fun ElitePasswordFieldWithErrorPreview() = ElitePasswordField(
    text = "passw",
    optionalPlaceholderText = "Your password",
    modifier = Modifier.fillMaxWidth(),
    optionalLeadingIcon = R.drawable.ic_key,
    isError = true,
    optionalSupportingText = "Password is too short!"
) {}
