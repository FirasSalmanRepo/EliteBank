package com.elite.elitebank.ui.theme.component.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.data.model.BankCard
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary

@Composable
@Preview
fun CardList() {
    val cards = listOf(
        BankCard("1200.50 USD", "5674  5678 9012 3456", "Firas Salman", "12/26", "123"),
        BankCard("875.99 EUR",  "9876  5432 1098 7654", "John Doe", "10/25", "456"),
        BankCard("450.00 JOD",  "4567  1234 7890 3210", "Sara Ahmad", "03/27", "789"),
    )
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    LazyRow(
        state = listState,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(cards) { card ->
            FlippableCard(card = card)
        }
    }
}

@Composable
fun FlippableCard(card: BankCard) {
    var flipped by remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "rotation"
    )
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 8 * density
            }
            .clickable { flipped = !flipped },
        contentAlignment = Alignment.Center
    ) {
        if (rotation.value <= 90f) {
            FrontCardView(card)
        } else {
            BackCardView(card)
        }
    }

    //  FrontCardView(card)
}

@Composable
fun FrontCardView(card: BankCard) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp)
            ) {
                Balance(Color.Blue, 22.sp, card)
                Spacer(modifier = Modifier.height(30.dp))
                CardNumberLabel(20.sp, card)
                Spacer(modifier = Modifier.height(40.dp))
                CardHolderSection(card)
            }
        }
    }
}

@Composable
fun Balance(background: Color, fontSize: TextUnit, card: BankCard) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EliteLabelPrimary(
            caption = card.balance,
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
fun CardNumberLabel(fontSize: TextUnit, card: BankCard) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardNumberLabel(
            card.cardNumber.take(4),
            fontSize = fontSize
        )
        CardNumberLabel(fontSize = fontSize)
        CardNumberLabel(fontSize = fontSize)
        CardNumberLabel(
            card.cardNumber.takeLast(4),
            fontSize = fontSize
        )
    }
}

@Composable
fun CardHolderSection(card: BankCard) {
    Column() {
        EliteLabelPrimary(
            caption = stringResource(id = R.string.name),
            color = Color.White,
            fontSize = 14.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            EliteLabelPrimary(
                caption = card.holderName,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            EliteLabelPrimary(caption = "Exp: ${card.expiryDate}", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BackCardView(card: BankCard) {
    Card(
        backgroundColor = Color(Color.Blue.value),
        modifier = Modifier
            .fillMaxSize()
            .rotate(180f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            EliteLabelPrimary(
                caption = "CVV: ${card.cvv}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Pre() = CardList()