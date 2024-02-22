package com.gentalha.cadecrypto.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gentalha.cadecrypto.presentation.ExchangeUiState
import com.gentalha.cadecrypto.ui.components.ExchangeCard
import com.gentalha.cadecrypto.ui.theme.BlueLight
import com.gentalha.cadecrypto.ui.theme.DarkBlue

@Composable
fun ExchangesScreen(
    uiState: ExchangeUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(horizontal = 16.dp)
    ) {
        when (uiState) {
            ExchangeUiState.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center),
                    color = BlueLight
                )
            }

            ExchangeUiState.Empty -> {
                Text(
                    "Nenhuma Exchange encontrada...",
                    modifier = Modifier.align(Alignment.Center),
                    color = BlueLight
                )
            }

            is ExchangeUiState.Failure -> {
                Text(
                    "Desculpe, algo deu errado! :(",
                    modifier = Modifier.align(Alignment.Center),
                    color = BlueLight
                )
            }

            is ExchangeUiState.Success -> {
                LazyColumn {
                    items(uiState.exchanges) { exchange ->
                        ExchangeCard(exchange = exchange)
                    }
                }
            }
        }
    }

}
