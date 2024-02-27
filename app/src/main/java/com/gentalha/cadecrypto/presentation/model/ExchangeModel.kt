package com.gentalha.cadecrypto.presentation.model

import com.gentalha.cadecrypto.cache.model.ExchangeEntity

data class ExchangeModel(
    val name: String,
    val id: String,
    val volumeDayUsd: Int,
    val icon: String,
    var isFavorite: Boolean = false
)

fun ExchangeEntity.toModel() = ExchangeModel(
    name = this.name,
    id = this.exchangeId,
    volumeDayUsd = this.volumeDayUsd,
    icon = this.icon,
    isFavorite = this.isFavorite
)

fun ExchangeModel.toEntity() = ExchangeEntity(
    name = this.name,
    exchangeId = this.id,
    volumeDayUsd = this.volumeDayUsd,
    icon = this.icon,
    isFavorite = this.isFavorite
)
