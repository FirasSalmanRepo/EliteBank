package com.elite.elitebank.ui.theme.component.dividers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.ui.theme.EliteColors


@Composable
@Preview
fun EliteDivider(
    modifier: Modifier = Modifier,
    optionalDividerColor: Color? = null
) = HorizontalDivider(
    modifier = modifier,
    thickness = 1.dp,
    color = optionalDividerColor ?: EliteColors.divider.default
)

@Preview
@Composable
private fun DXDividerPreview() = EliteDivider(
    modifier = Modifier.fillMaxWidth(),
    optionalDividerColor = Color.Red
)
