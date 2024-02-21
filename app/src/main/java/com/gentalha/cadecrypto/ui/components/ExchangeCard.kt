package com.gentalha.cadecrypto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.cadecrypto.R
import com.gentalha.cadecrypto.presentation.model.ExchangeModel
import com.gentalha.cadecrypto.ui.theme.BlueGray
import com.gentalha.cadecrypto.ui.theme.BlueLight

@Composable
fun ExchangeCard(modifier: Modifier = Modifier, exchange: ExchangeModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable { println("Cliquei no ${exchange.id}") },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(BlueGray, RoundedCornerShape(8.dp))
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.place_holder),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = exchange.name,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                color = Color.White
            )
            Text(
                text = "$${exchange.volumeDayUsd}B 24h volume",
                fontSize = 14.sp,
                color = BlueLight
            )
        }
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = null,
            tint = Color.White
        )
    }
}