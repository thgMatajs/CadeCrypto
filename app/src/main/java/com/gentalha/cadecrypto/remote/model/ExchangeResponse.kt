package com.gentalha.cadecrypto.remote.model

import com.gentalha.cadecrypto.cache.model.ExchangeEntity
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeResponse(
    @SerializedName("exchange_id")
    val exchangeId: String,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("volume_1day_usd")
    val volumeDayUsd: BigDecimal
)

fun ExchangeResponse.toEntity() = ExchangeEntity(
    this.name ?: "",
    this.exchangeId,
    this.volumeDayUsd.toInt(),
    ""
)
