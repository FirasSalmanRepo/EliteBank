package com.elite.elitebank.ui.theme.component.inputs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.regular
import com.elite.elitebank.ui.theme.textField
import com.elite.elitebank.ui.theme.textFieldPlaceholder

@Composable
fun EliteTextField(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    isError: Boolean = false,
    isSingleLine: Boolean = false,
    isPasswordField: Boolean = false,
    optionalSupportingText: String? = null,
    optionalPlaceholderText: String? = null,
    @DrawableRes optionalLeadingIcon: Int? = null,
    @DrawableRes optionalTrailingIcon: Int? = null,
    optionalKeyboardOptions: KeyboardOptions? = null,
    optionalKeyboardActions: KeyboardActions? = null,
    onChange: (String) -> Unit
) {

    var isPasswordVisible: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    val trailingIcon: (@Composable () -> Unit)? = if (isPasswordField) {
        {
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                Icon(
                    painter = painterResource(id = optionalTrailingIcon ?: R.drawable.ic_eye),
                    contentDescription = null
                )
            }
        }
    } else if (optionalTrailingIcon != null) {
        {
            Icon(
                painter = painterResource(id = optionalTrailingIcon),
                contentDescription = null
            )
        }
    } else {
        null
    }

    OutlinedTextField(
        value = text,
        isError = isError,
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = textField(),
        onValueChange = onChange,
        singleLine = isSingleLine,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = optionalKeyboardOptions ?: KeyboardOptions.Default,
        keyboardActions = optionalKeyboardActions ?: KeyboardActions.Default,
        leadingIcon = optionalLeadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
        },
        trailingIcon = trailingIcon,
        placeholder = optionalPlaceholderText?.let {
            {
                Text(
                    text = it,
                    style = textFieldPlaceholder()
                )
            }
        },
        supportingText = optionalSupportingText?.let {
            {
                Text(
                    text = it,
                    style = regular()
                )
            }
        },
        visualTransformation = when {
            isPasswordField -> if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }

            else -> VisualTransformation.None
        },
        colors = OutlinedTextFieldDefaults.colors(

            focusedTextColor = EliteColors.textField.focusedText,
            unfocusedTextColor = EliteColors.textField.focusedText,
            errorTextColor = EliteColors.textField.error,

            focusedBorderColor = EliteColors.textField.focusedBorder,
            unfocusedBorderColor = EliteColors.textField.unfocusedBorder,
            errorBorderColor = EliteColors.textField.error,

            focusedContainerColor = EliteColors.textField.container,
            unfocusedContainerColor = EliteColors.textField.container,
            errorContainerColor = EliteColors.textField.container,
            disabledContainerColor = EliteColors.textField.container,

            focusedLeadingIconColor = EliteColors.textField.placeholder,
            unfocusedLeadingIconColor = EliteColors.textField.placeholder,
            errorLeadingIconColor = EliteColors.textField.error,

            focusedTrailingIconColor = EliteColors.textField.placeholder,
            unfocusedTrailingIconColor = EliteColors.textField.placeholder,
            errorTrailingIconColor = EliteColors.textField.error,

            focusedSupportingTextColor = EliteColors.textField.placeholder,
            unfocusedSupportingTextColor = EliteColors.textField.placeholder,
            errorSupportingTextColor = EliteColors.textField.error,

            focusedPlaceholderColor = EliteColors.textField.placeholder,
            unfocusedPlaceholderColor = EliteColors.textField.placeholder,
            errorPlaceholderColor = EliteColors.textField.error,

            cursorColor = EliteColors.textField.cursor,
            errorCursorColor = EliteColors.textField.error,
            selectionColors = TextSelectionColors(
                handleColor = EliteColors.textField.selectionHandle,
                backgroundColor = EliteColors.textField.selectionHandle.copy(alpha = .4f)
            )
        )
    )
}

@Preview
@Composable
private fun EliteTextFieldEmptyPreview() = EliteTextField(
    text = "",
    optionalPlaceholderText = "Your name here",
    optionalTrailingIcon = R.drawable.ic_account,
    modifier = Modifier.fillMaxWidth()
) {}

@Preview
@Composable
private fun EliteTextFieldWithTextPreview() = EliteTextField(
    text = "FirasSalman@gmail.com",
    optionalPlaceholderText = "Your email here",
    modifier = Modifier.fillMaxWidth(),
    optionalLeadingIcon = R.drawable.ic_mail
) {}

@Preview
@Composable
private fun EliteTextFieldWithErrorTextPreview() = EliteTextField(
    text = "FirasSalman@gmail.com",
    optionalPlaceholderText = "Enter your email",
    modifier = Modifier.fillMaxWidth(),
    optionalLeadingIcon = R.drawable.ic_key,
    isError = true,
    optionalSupportingText = "Please enter a valid email!"
) {}
