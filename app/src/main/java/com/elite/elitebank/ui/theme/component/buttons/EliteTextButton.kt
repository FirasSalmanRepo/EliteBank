package com.elite.elitebank.ui.theme.component.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.button
import com.elite.elitebank.ui.theme.utils.semanticModifier


@Composable
fun EliteTextButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    layoutId: String? = null,
    contentDescription: String? = null,
    onClickAction: () -> Unit
) = TextButton(
    onClick = onClickAction,
    enabled = isEnable,
    modifier = modifier.semanticModifier(
        contentDescription ?: text,
        enabled = isEnable,
        role = Role.Button,
        layoutId = layoutId
    ),
    colors = ButtonDefaults.textButtonColors(
        contentColor = EliteColors.text.light,
        disabledContentColor = EliteColors.text.disabled
    )
) {
    Text(
        text = text,
        style = button()
    )
}

@Preview
@Composable
private fun EliteTextButtonEnabledPreview() = EliteTextButton(
    text = "Skip"
) {}

@Preview
@Composable
private fun EliteTextButtonDisabledPreview() = EliteTextButton(
    text = "Skip",
    isEnable = false
) {}
