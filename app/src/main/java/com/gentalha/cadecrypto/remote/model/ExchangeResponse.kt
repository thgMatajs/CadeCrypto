package com.gentalha.cadecrypto.remote.model

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
