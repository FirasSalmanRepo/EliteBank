package com.elite.elitebank.ui.theme.component.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.EliteColors.gray4
import com.elite.elitebank.ui.theme.fonts


@Composable
@Preview
fun AppCurrenciesDropDown() {
    val list = listOf("$ USD", "LE", "JOD", "SAR", "Euro")
    val isExpanded = remember { mutableStateOf(false) }
    val currentValue = remember {
        mutableStateOf(list[0])
    }
    Row(
        modifier = Modifier.clickable { isExpanded.value = !isExpanded.value },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = currentValue.value)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "drop",
            tint = gray4
        )
        Spacer(modifier = Modifier.width(8.dp))

        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }) {
            list.forEach {
                DropdownMenuItem(onClick = {
                    currentValue.value = it
                    isExpanded.value = false
                }) {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

