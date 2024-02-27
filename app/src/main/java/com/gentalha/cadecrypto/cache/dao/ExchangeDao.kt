package com.gentalha.cadecrypto.cache.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.gentalha.cadecrypto.cache.model.ExchangeEntity

@Dao
interface ExchangeDao {

    @Query("SELECT * FROM exchanges")
    suspend fun get(): List<ExchangeEntity>

    @Query("SELECT * FROM exchanges WHERE isFavorite = 1")
    suspend fun getFavorites(): List<ExchangeEntity>

    @Update
    suspend fun add(exchangeEntity: ExchangeEntity)
}
