package com.gentalha.cadecrypto.remote.di

import com.gentalha.cadecrypto.common.network.ServiceBuilder
import com.gentalha.cadecrypto.remote.service.CoinApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideCoinApiService() = ServiceBuilder().invoke<CoinApiService>(" https://rest.coinapi.io")
}