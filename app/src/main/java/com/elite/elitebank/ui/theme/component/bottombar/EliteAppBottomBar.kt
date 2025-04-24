package com.elite.elitebank.ui.theme.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.navigation.NavRoute
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.R

@Composable
fun EliteAppBottomBar(
    isBottomBarVisible: Boolean,
    isTabSelected: @Composable (String) -> Boolean,
    onTabClicked: (String) -> Unit
) = AnimatedVisibility(visible = isBottomBarVisible) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = EliteColors.card.white,
            tonalElevation = 1.dp
        ) {

            listOf(
                Triple("Home", NavRoute.HOME.ROOT, R.drawable.ic_home),
                Triple("Transfer", NavRoute.TransferBANK.MAIN, R.drawable.ic_transfer),
                Triple("More", NavRoute.Menu.LIST, R.drawable.ic_more),
            ).forEach { dataTriple ->

                val isSelected = isTabSelected(dataTriple.second)

                EliteAppBottomBarItem(
                    displayName = dataTriple.first,
                    icon = dataTriple.third,
                    isSelected = isSelected,
                    onClicked = {
                        onTabClicked(dataTriple.second)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun EliteAppBottomBarPreviewHome() = EliteAppBottomBar(
    isBottomBarVisible = true,
    isTabSelected = {
        it == NavRoute.HOME.ROOT
    }
) {}

@Preview
@Composable
private fun EliteAppBottomBarPreviewDataBank() = EliteAppBottomBar(
    isBottomBarVisible = true,
    isTabSelected = {
        it == NavRoute.TransferBANK.MAIN
    }
) {}

@Preview
@Composable
private fun EliteAppBottomBarPreviewMore() = EliteAppBottomBar(
    isBottomBarVisible = true,
    isTabSelected = {
        it == NavRoute.Menu.LIST
    }
) {}
