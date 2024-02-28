package com.gentalha.cadecrypto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gentalha.cadecrypto.presentation.model.ExchangeModel
import com.gentalha.cadecrypto.presentation.state.FavoriteUiState
import com.gentalha.cadecrypto.ui.components.ExchangeCard
import com.gentalha.cadecrypto.ui.theme.BlueLight

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onFavoriteOnClick: (ExchangeModel) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            FavoriteUiState.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.CenterHorizontally),
                    color = BlueLight
                )
            }

            FavoriteUiState.Empty -> {
                Text(
                    "Nenhuma Exchange encontrada...",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = BlueLight
                )
            }

            is FavoriteUiState.Failure -> {
                Text(
                    "Desculpe, algo deu errado! :(",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = BlueLight
                )
            }

            is FavoriteUiState.Success -> {
                LazyColumn {
                    items(uiState.exchanges, key = { key -> key.id }) { exchange ->
                        ExchangeCard(
                            exchange = exchange,
                            onFavoriteClick = onFavoriteOnClick::invoke
                        )
                    }
                }
            }
        }
    }
}