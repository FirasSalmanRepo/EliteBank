package com.elite.elitebank.ui.theme.component.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun EliteIllustration(
    @DrawableRes illustration: Int,
    modifier: Modifier = Modifier,
    optionalContentScale: ContentScale = ContentScale.Fit,
    optionalContentDescription: String? = null
) = Image(
    painter = painterResource(id = illustration),
    contentDescription = optionalContentDescription,
    modifier = modifier,
    contentScale = optionalContentScale
)
