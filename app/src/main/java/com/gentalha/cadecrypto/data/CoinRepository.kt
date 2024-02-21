package com.gentalha.cadecrypto.data

import com.gentalha.cadecrypto.remote.service.CoinApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(private val service: CoinApiService) {

    fun getExchanges() = flow {
        emit(service.getExchanges())
    }

}
