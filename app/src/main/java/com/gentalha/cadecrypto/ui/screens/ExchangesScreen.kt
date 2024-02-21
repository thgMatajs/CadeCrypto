package com.gentalha.cadecrypto.ui.screens

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

@Composable
fun ExchangesScreen(
    uiState: ExchangeUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        ExchangeUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center),
                    color = BlueLight
                )
            }
        }

        ExchangeUiState.Empty -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Nenhuma Exchange encontrada...",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is ExchangeUiState.Failure -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Desculpe, algo deu errado! :(",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is ExchangeUiState.Success -> {
            Box(modifier = modifier.padding(horizontal = 16.dp)) {
                LazyColumn {
                    items(uiState.exchanges) { exchange ->
                        ExchangeCard(exchange = exchange)
                    }
                }
            }
        }
    }
}