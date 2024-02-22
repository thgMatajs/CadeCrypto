package com.gentalha.cadecrypto.remote.service

import com.gentalha.cadecrypto.remote.model.ExchangeIconResponse
import com.gentalha.cadecrypto.remote.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {

    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @GET("v1/exchanges/icons/{size}")
    suspend fun getExchangeLogo(@Path("size") size: Int = 4): List<ExchangeIconResponse>
}
