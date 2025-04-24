package com.elite.elitebank.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.elite.elitebank.R
import com.elite.elitebank.data.model.Transaction
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary
import com.elite.elitebank.ui.theme.viewmodels.AppViewModel

@Composable
fun Transactions() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(title = stringResource(id = R.string.recent_transactions), viewAll = true)
        Spacer(modifier = Modifier.height(16.dp))
        History()
    }
}

@Composable
fun History() {
    val viewModel = viewModel<AppViewModel>()
    val result by viewModel.transactionListFlow.collectAsState(initial = null)
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        result?.forEach {
            GenerateItem(it)
        }
    }
}

@Composable
fun GenerateItem(item: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(8.dp, 12.dp, 8.dp, 8.dp)
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            EliteLabelPrimary(caption = item.name, fontSize = 14.sp)
            EliteLabelPrimary(caption = item.date, color = EliteColors.gray2, fontSize = 12.sp)
        }

        if (item.isDebit) {
            EliteLabelPrimary(caption = item.amount, color = EliteColors.green, fontSize = 18.sp)
        } else {
            EliteLabelPrimary(caption = item.amount, color = EliteColors.red, fontSize = 18.sp)
        }
    }
}
