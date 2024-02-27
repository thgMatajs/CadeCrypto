package com.gentalha.cadecrypto.cache.di

import android.content.Context
import androidx.room.Room
import com.gentalha.cadecrypto.cache.database.ExchangeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideExchangeDatabase(@ApplicationContext context: Context): ExchangeDatabase {
        return Room.databaseBuilder(context, ExchangeDatabase::class.java, "exchange-db")
            .build()
    }

    @Provides
    fun provideExchangeDao(database: ExchangeDatabase) = database.exchangeDao()
}
