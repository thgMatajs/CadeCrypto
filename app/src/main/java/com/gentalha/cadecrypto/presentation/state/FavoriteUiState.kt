package com.gentalha.cadecrypto.presentation.state

import com.gentalha.cadecrypto.presentation.model.ExchangeModel

sealed class FavoriteUiState {

    data object Loading : FavoriteUiState()

    data object Empty : FavoriteUiState()

    data class Success(
        val exchanges: List<ExchangeModel> = emptyList()
    ) : FavoriteUiState()

    data class Failure(
        val error: Throwable
    ) : FavoriteUiState()
}