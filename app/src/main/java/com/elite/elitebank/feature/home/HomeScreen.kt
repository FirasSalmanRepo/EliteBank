package com.elite.elitebank.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.Paddings
import com.elite.elitebank.ui.theme.component.SectionButton
import com.elite.elitebank.ui.theme.component.Transactions
import com.elite.elitebank.ui.theme.component.appbar.AppTopBarPrimary
import com.elite.elitebank.ui.theme.component.cards.CreditCard
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary


@Composable
fun HomeScreen(
    state: HomeScreenState,
    onEvent: (HomeEvent) -> Unit
) = HomeScreenContent(
    state = state,
    onEvent = onEvent
)

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AppTopBarPrimary()
        Column(modifier = Modifier.padding(Paddings.medium)) {
            EliteLabelPrimary(
                caption = "Welcome \uD83D\uDC4B",
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            EliteLabelPrimary(
                caption = state.userName,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Start,
                color = EliteColors.text.dark,
            )
            Spacer(modifier = Modifier.height(Paddings.small))
        }
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            CreditCard()
        }
        SectionButton(onEvent)
        Transactions()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() = HomeScreen(
    state = HomeScreenState(
        userName = "Firas Salman",
        isLoading = false
    )
) {}

