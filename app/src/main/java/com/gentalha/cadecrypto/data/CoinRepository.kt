package com.gentalha.cadecrypto.data

import com.gentalha.cadecrypto.cache.dao.ExchangeDao
import com.gentalha.cadecrypto.remote.model.toEntity
import com.gentalha.cadecrypto.remote.service.CoinApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val service: CoinApiService,
    private val exchangeDao: ExchangeDao
) {

    fun getExchanges() = flow {
        val exchanges = service.getExchanges()
        exchangeDao.add(exchanges.map { it.toEntity() })
        emit(exchangeDao.get())
    }

    fun getExchangeIcon() = flow {
        emit(service.getExchangeLogo())
    }

    fun getExchangesBy(id: String) = flow {
        emit(exchangeDao.searchBy(id))
    }

}
