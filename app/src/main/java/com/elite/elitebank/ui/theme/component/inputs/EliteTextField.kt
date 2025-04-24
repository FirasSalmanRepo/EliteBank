package com.elite.elitebank.ui.theme.component.inputs

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.elite.elitebank.ui.theme.EliteColors.gray2
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary

@Composable
fun EliteTextField(
    hint: String, maxLines: Int = 1, modifier: Modifier = Modifier,
    hintColor: Color = gray2, textColor: Color = Color.Black,
    data: String, action: (String) -> Unit
) {
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Gray,
        disabledTextColor = Color.LightGray,
    )

    TextField(
        modifier = modifier,
        value = data,
        label = null,
        colors = colors,
        textStyle = TextStyle(color = textColor),
        maxLines = maxLines,
        placeholder = {
            EliteLabelPrimary(caption = hint, color = hintColor, fontSize = 12.sp)
        },
        onValueChange = {
            action(it)
        })
}

@Preview
@Composable
private fun EliteTextFieldPreview() = EliteTextField(
    text = "firas salman",
) {}
