package com.elite.elitebank.ui.theme.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors.blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubAppTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Money  Transfer") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = blue,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Do something */ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_more_vertical), contentDescription = "",
                    tint = Color.White
                )
            }
        }
    )
}