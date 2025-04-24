package com.elite.elitebank.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.feature.home.HomeEvent
import com.elite.elitebank.ui.theme.EliteColors.gray2
import com.elite.elitebank.ui.theme.EliteColors.gray3
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary


@Composable
fun SectionButton(onEvent: (HomeEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BigButton(stringResource(id = R.string.transfer), R.drawable.ic_transfer) {
            onEvent(HomeEvent.TransferClicked)
        }
        BigButton(stringResource(id = R.string.payments), R.drawable.ic_payment) {
            onEvent(HomeEvent.PaymentsClicked)
        }
        BigButton(stringResource(id = R.string.add_card), R.drawable.ic_add_card) {
            onEvent(HomeEvent.AddCardClicked)
        }
        BigButton(stringResource(id = R.string.more), R.drawable.ic_more) {
            onEvent(HomeEvent.MenuClicked)
        }
    }

}

@Composable
fun BigButton(title: String, icon: Int, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(5.dp)) {
        Box(
            modifier = Modifier
                .width(76.dp)
                .height(76.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "transfer",
                    tint = gray3,
                    modifier = Modifier.size(33.dp, 33.dp)
                )
                Title(text = title)
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

@Composable
fun Title(text: String) {
    EliteLabelPrimary(
        caption = text,
        fontSize = 12.sp,
        color = gray2,
        modifier = Modifier.padding(4.dp)
    )
}

@Preview(showSystemUi = true, backgroundColor = 0XFFE5EBF2)
@Composable
fun Prev() = SectionButton(onEvent = {})

