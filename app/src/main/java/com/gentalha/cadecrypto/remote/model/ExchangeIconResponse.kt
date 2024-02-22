package com.gentalha.cadecrypto.remote.model

import com.google.gson.annotations.SerializedName

data class ExchangeIconResponse(
    @SerializedName("exchange_id")
    val exchangeId: String?,
    @SerializedName("url")
    val url: String?
)
