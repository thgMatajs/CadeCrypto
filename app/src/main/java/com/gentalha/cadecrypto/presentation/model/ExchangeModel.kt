package com.gentalha.cadecrypto.presentation.model

import com.gentalha.cadecrypto.cache.model.ExchangeEntity
import com.gentalha.cadecrypto.remote.model.ExchangeResponse

data class ExchangeModel(
    val name: String,
    val id: String,
    val volumeDayUsd: Int,
    val icon: String,
    var isFavorite: Boolean = false
)

fun ExchangeResponse.toModel(url: String) = ExchangeModel(
    name = this.name ?: "",
    id = this.exchangeId,
    volumeDayUsd = this.volumeDayUsd.toInt(),
    icon = url
)

fun ExchangeEntity.toModel(urlIcon: String) = ExchangeModel(
    name = this.name,
    id = this.exchangeId,
    volumeDayUsd = this.volumeDayUsd,
    icon = urlIcon
)
