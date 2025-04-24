package com.elite.elitebank.feature.onboarding.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.component.images.EliteGifImage
import com.elite.elitebank.ui.theme.component.images.EliteIllustration
import com.elite.elitebank.R.drawable as UIDrawables

@Composable
fun SplashScreen() = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(EliteColors.screenBackground.secondary),
    contentAlignment = Alignment.Center
) {
    EliteIllustration(
        illustration = R.drawable.splash_bg,
        modifier = Modifier.fillMaxSize(),
        optionalContentScale = ContentScale.FillBounds
    )
    EliteGifImage(modifier = Modifier.size(250.dp),
        data = UIDrawables.ic_elite_logo)
}

@Preview
@Composable
private fun SplashScreenPreview() = SplashScreen()