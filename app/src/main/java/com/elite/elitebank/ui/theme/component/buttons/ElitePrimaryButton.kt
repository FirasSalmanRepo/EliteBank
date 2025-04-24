package com.elite.elitebank.ui.theme.component.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.Paddings
import com.elite.elitebank.ui.theme.button
import com.elite.elitebank.ui.theme.utils.semanticModifier

@Composable
fun ElitePrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    layoutId: String? = null,
    contentDescription: String? = null,
    onClickAction: () -> Unit
) = ElevatedButton(
    onClick = onClickAction,
    enabled = isEnable,
    modifier = modifier
        .semanticModifier(
            contentDescription ?: text,
            enabled = isEnable,
            role = Role.Button,
            layoutId = layoutId
        ),
    shape = RoundedCornerShape(12.dp),
    colors = ButtonDefaults.elevatedButtonColors(
        containerColor = EliteColors.primary.default,
        contentColor = Color.White,
        disabledContainerColor = EliteColors.primary.light,
        disabledContentColor = EliteColors.text.disabled
    )
) {
    Text(
        text = text,
        style = button(),
        modifier = Modifier
            .padding(Paddings.small)
            .align(Alignment.CenterVertically)
    )
}

@Preview
@Composable
private fun ElitePrimaryButtonEnabledPreview() = ElitePrimaryButton(text = "Get Started") {}

@Preview
@Composable
private fun ElitePrimaryButtonDisabledPreview() = ElitePrimaryButton(
    text = "Get Started",
    isEnable = false
) {}
