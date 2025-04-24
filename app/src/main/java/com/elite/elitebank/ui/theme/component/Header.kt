package com.elite.elitebank.ui.theme.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.ui.theme.component.labels.EliteLabelPrimary

@Composable
fun Header(title:String,viewAll:Boolean){

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        if(viewAll){
            EliteLabelPrimary(caption = title,color= Color.Black,modifier = Modifier.weight(1f), fontSize = 16.sp)
            EliteLabelPrimary(caption = stringResource(id = R.string.view_all),color=Color.Black, fontSize = 12.sp)
        }else{
            EliteLabelPrimary(caption = title,color=Color.White,modifier = Modifier.weight(1f), fontSize = 16.sp)
        }
    }
}