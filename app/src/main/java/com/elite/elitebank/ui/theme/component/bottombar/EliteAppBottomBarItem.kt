package com.elite.elitebank.ui.theme.component.bottombar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.elite.elitebank.ui.theme.EliteColors

@Composable
fun RowScope.EliteAppBottomBarItem(
    displayName: String,
    @DrawableRes icon: Int,
    isSelected: Boolean,
    onClicked: () -> Unit
) {

    NavigationBarItem(
        selected = isSelected,
        onClick = {
            if (!isSelected) {
                onClicked()
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = displayName,
                tint = if (isSelected) {
                    EliteColors.primary.default
                } else {
                    Color(0xFF6B7280)
                }
            )
        },
        label = {
            Text(
                text = displayName,
                color = if (isSelected) {
                    EliteColors.primary.default
                } else {
                    Color(0xFF6B7280)
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = NavigationBarItemDefaults.colors().copy(
            selectedIndicatorColor = EliteColors.card.white
        )
    )
}
