package com.gentalha.cadecrypto.remote.service

import com.gentalha.cadecrypto.remote.model.ExchangeResponse
import retrofit2.http.GET

interface CoinApiService {

    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>
}