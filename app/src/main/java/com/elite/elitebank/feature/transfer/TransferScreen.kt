package com.elite.elitebank.feature.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.elite.elitebank.R
import com.elite.elitebank.data.model.PopularPerson
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.component.Header
import com.elite.elitebank.ui.theme.component.SubAppTopBar
import com.elite.elitebank.ui.theme.component.buttons.ElitePrimaryButton
import com.elite.elitebank.ui.theme.component.cards.CardList
import com.elite.elitebank.ui.theme.component.dropdown.AppCurrenciesDropDown
import com.elite.elitebank.ui.theme.component.inputs.EliteTextField
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary
import com.elite.elitebank.ui.theme.viewmodels.AppViewModel


@Composable
fun TransferScreen(
    state: TransferState,
    onEvent: (TransferEvent) -> Unit
) = TransferScreenContent(state = state, onEvent = onEvent)


@Composable
fun TransferScreenContent(
    state: TransferState,
    onEvent: (TransferEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .background(EliteColors.blue)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SubAppTopBar()
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    NoteSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    ScanSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    PopularSection()

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        CardsSection()
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp, 12.dp, 0.dp)
        ) {
            SendSection(state = state, onEvent = onEvent)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun NoteSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(EliteColors.drakBlue2)
            .padding(16.dp)
    )
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = "info_icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {

                EliteLabelPrimary(
                    caption = stringResource(id = R.string.money_transfer_statistics),
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                EliteLabelPrimary(
                    caption = stringResource(id = R.string.money_transfer_statistics_note),
                    color = Color.White,
                    lineHeight = 20.sp,
                    fontSize = 12.sp
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "info_icon",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ScanSection() {

    var identity by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(EliteColors.drakBlue2)
            .padding(8.dp, 0.dp, 16.dp, 0.dp)
    )
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {


            EliteTextField(
                hint = stringResource(id = R.string.scan_input_hint),
                hintColor = Color.White,
                textColor = Color.White,
                data = identity,
                modifier = Modifier.weight(1f)
            ) {
                identity = it
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "info_icon",
                tint = Color.White
            )
        }
    }
}

@Composable
fun PopularSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(title = stringResource(id = R.string.beneficiary), viewAll = false)
        Spacer(modifier = Modifier.height(16.dp))
        History()
    }
}

@Composable
fun History() {
    val vm = viewModel<AppViewModel>()
    val result by vm.popularListFlow.collectAsState(initial = null)
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        result?.forEach {
            item { GenerateItem(it) }
        }
    }
}

@Composable
fun GenerateItem(item: PopularPerson) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        EliteLabelPrimary(
            caption = item.name,
            color = Color.White,
            fontSize = 11.sp,
            lineHeight = 10.sp
        )
    }
}


@Composable
fun CardsSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(title = stringResource(id = R.string.from_card), viewAll = true)
        Spacer(modifier = Modifier.height(16.dp))
        CardList()
    }
}

@Composable
fun SendSection(
    state: TransferState,
    onEvent: (TransferEvent) -> Unit
) {
    var amount by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .border(width = 1.dp, color = EliteColors.gray5, shape = RoundedCornerShape(14.dp))

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                EliteTextField(
                    hint = stringResource(id = R.string.amount),
                    data = amount,
                    modifier = Modifier.weight(1f)
                ) {
                    amount = it
                }

                AppCurrenciesDropDown()

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ElitePrimaryButton(
            text = stringResource(id = R.string.send_money),
            isEnable = state.isTransferButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            onEvent(TransferEvent.OnTransferClicked)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Prv() {
    val viewModel: TransferViewModel = viewModel()
    val state = viewModel.state
    TransferScreen(state = viewModel.state, viewModel::onEvent)
}

