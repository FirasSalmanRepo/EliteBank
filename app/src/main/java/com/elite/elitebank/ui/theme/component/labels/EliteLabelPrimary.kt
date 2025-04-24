package com.elite.elitebank.ui.theme.component.labels


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.elite.elitebank.ui.theme.fonts

@Composable
fun EliteLabelPrimary(
    caption: String = "unnamed",
    color: Color = Color.Black,
    fontSize: TextUnit,
    lineHeight: TextUnit = 22.sp,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    padding: Int = 0
) {
    Text(
        text = caption,
        color = color,
        fontSize = fontSize,
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        lineHeight = lineHeight,
        modifier = modifier,
    )
}