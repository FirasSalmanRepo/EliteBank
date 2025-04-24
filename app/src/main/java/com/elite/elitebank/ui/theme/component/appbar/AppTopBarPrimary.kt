package com.elite.elitebank.ui.theme.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors.darkGray
import com.elite.elitebank.ui.theme.component.images.EliteIllustration


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppTopBarPrimary() {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.home)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        actions = {
            IconButton(onClick = { }) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(darkGray)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                ){
                    EliteIllustration(
                        illustration = R.drawable.ic_salman_image,
                        modifier = Modifier.width(150.dp) .height(150.dp),
                        optionalContentScale = ContentScale.FillBounds
                    )
                }


            }
        }
    )
}