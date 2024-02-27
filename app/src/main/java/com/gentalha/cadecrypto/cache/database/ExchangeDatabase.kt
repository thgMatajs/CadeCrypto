package com.gentalha.cadecrypto.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gentalha.cadecrypto.cache.dao.ExchangeDao
import com.gentalha.cadecrypto.cache.model.ExchangeEntity

@Database(entities = [ExchangeEntity::class], version = 1)
abstract class ExchangeDatabase : RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao
}
