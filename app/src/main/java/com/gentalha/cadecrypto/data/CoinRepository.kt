package com.gentalha.cadecrypto.data

import com.gentalha.cadecrypto.cache.dao.ExchangeDao
import com.gentalha.cadecrypto.cache.model.ExchangeEntity
import com.gentalha.cadecrypto.remote.model.toEntity
import com.gentalha.cadecrypto.remote.service.CoinApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val service: CoinApiService,
    private val exchangeDao: ExchangeDao
) {

    fun getExchanges() = flow {
        if (exchangeDao.get().isEmpty()) {
            val exchangesResponse = service.getExchanges()
            val iconsResponse = service.getExchangeLogo()
            val exchangeEntities = exchangesResponse.map { exchange ->
                val icon = iconsResponse.firstOrNull { icon ->
                    icon.exchangeId == exchange.exchangeId
                }
                exchange.toEntity().copy(icon = icon?.url ?: "")
            }
            exchangeDao.add(exchangeEntities)
        }
        emit(exchangeDao.get())
    }

    fun getExchangesBy(id: String) = flow {
        emit(exchangeDao.searchBy(id))
    }

    suspend fun addFavorite(exchange: ExchangeEntity) = exchangeDao.update(exchange)

}
