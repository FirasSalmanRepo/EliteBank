package com.elite.elitebank.ui.theme.component.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elite.elitebank.ui.theme.CardValues
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.utils.semanticModifier

@Composable
fun EliteWhiteCard(
    modifier: Modifier = Modifier,
    cardCornerRadius: Dp = CardValues.cornerRadius,
    cardElevation: Dp = CardValues.elevation,
    cardColor: Color = EliteColors.card.white,
    contentDescription: String? = null,
    onClickAction: (() -> Unit)? = null,
    content: @Composable () -> Unit
) = if (onClickAction != null) {
    Card(
        modifier = modifier.semanticModifier(contentDescription, role = Role.Button),
        shape = RoundedCornerShape(corner = CornerSize(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(cardElevation),
        onClick = onClickAction
    ) {
        content.invoke()
    }
} else {
    Card(
        modifier = modifier.semanticModifier(contentDescription),
        shape = RoundedCornerShape(corner = CornerSize(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(cardElevation)
    ) {
        content.invoke()
    }
}

@Preview
@Composable
private fun EliteCardPreview() = EliteWhiteCard(
    Modifier.fillMaxWidth()
) {
    Text(
        text = "Hello!",
        modifier = Modifier.padding(16.dp),
        color = EliteColors.text.dark
    )
}
