package com.gentalha.cadecrypto.presentation.model

import com.gentalha.cadecrypto.remote.model.ExchangeResponse
import java.math.BigDecimal

data class ExchangeModel(
    val name: String,
    val id: String,
    val volumeDayUsd: BigDecimal,
    val icon: String
)

fun ExchangeResponse.toModel(url: String) = ExchangeModel(
    name = this.name ?: "",
    id = this.exchangeId,
    volumeDayUsd = this.volumeDayUsd,
    icon = url
)
