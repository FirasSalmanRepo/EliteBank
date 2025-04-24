package com.elite.elitebank.ui.theme.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors.blue
import com.elite.elitebank.ui.theme.EliteColors.drakBlue
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary



@Composable
fun CreditCard(
    background: Color = blue, modifier: Modifier = Modifier, amountBackground: Color = drakBlue,
    amountFontSize: TextUnit = 22.sp, cardNumberFontSize: TextUnit = 30.sp
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(230.dp)
                .background(background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp)
            ) {
                Amount(amountBackground, amountFontSize)
                Spacer(modifier = Modifier.height(30.dp))
                CardNumberSection(amountFontSize)
                Spacer(modifier = Modifier.height(40.dp))
                CardHolderSection()
            }
        }
    }
}

@Composable
fun Amount(background: Color, fontSize: TextUnit) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        EliteLabelPrimary(
            caption = stringResource(id = R.string.amount_number1),
            color = Color.White,
            fontSize = fontSize,
            modifier = Modifier.weight(3f)
        )
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                .width(50.dp)
                .height(30.dp)
                .background(color = background)
        )
    }
}

@Composable
fun CardNumberSection(fz: TextUnit) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        CardNumberLabel(fontSize = fz)
        CardNumberLabel(fontSize = fz)
        CardNumberLabel(fontSize = fz)
        CardNumberLabel("5004", fontSize = fz)
    }
}

@Composable
fun CardNumberLabel(code: String = "****", fontSize: TextUnit) {
    EliteLabelPrimary(
        caption = code,
        color = Color.White,
        fontSize = fontSize,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun CardHolderSection() {
    Column() {
        EliteLabelPrimary(
            caption = stringResource(id = R.string.name),
            color = Color.White,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            CardHolderLabel()
            ExpireDateLabel()
        }
    }
}

@Composable
fun CardHolderLabel() {
    EliteLabelPrimary(caption = stringResource(id = R.string.my_name), color = Color.White, fontSize = 14.sp)
}

@Composable
fun ExpireDateLabel() {
    EliteLabelPrimary(caption = "05/28", color = Color.White, fontSize = 14.sp)
}

//@Preview(showSystemUi = true)
//@Composable
//fun Pre() = CreditCard()
