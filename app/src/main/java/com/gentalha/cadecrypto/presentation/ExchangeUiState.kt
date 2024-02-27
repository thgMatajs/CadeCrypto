package com.gentalha.cadecrypto.presentation

import com.gentalha.cadecrypto.presentation.model.ExchangeModel

sealed class ExchangeUiState {

    data object Loading : ExchangeUiState()

    data object Empty : ExchangeUiState()

    data object SearchEmpty : ExchangeUiState()
    data class Success(
        val exchanges: List<ExchangeModel> = emptyList()
    ) : ExchangeUiState()

    data class Failure(
        val err: Throwable
    ) : ExchangeUiState()
}
