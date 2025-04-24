package com.elite.elitebank.ui.theme.component.background

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.elite.elitebank.ui.theme.EliteColors

@Composable
fun EliteSurface(
    modifier: Modifier = Modifier,
    bgColor: Color = EliteColors.screenBackground.primary,
    content: @Composable () -> Unit
) = Surface(
    modifier = modifier,
    color = bgColor
) {
    val scrollState = rememberScrollState()
    modifier.verticalScroll(scrollState)
    content()
}

@PreviewLightDark
@Composable
private fun EliteSurfacePreview() = EliteSurface(
    modifier = Modifier.fillMaxSize()
) {}
