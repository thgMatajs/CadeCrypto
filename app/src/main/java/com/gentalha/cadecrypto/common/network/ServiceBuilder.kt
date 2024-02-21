package com.gentalha.cadecrypto.common.network

import com.gentalha.cadecrypto.common.key.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilder {
    inline operator fun <reified S> invoke(baseUrl: String, isDebugging: Boolean = true): S {
        val httpClient = buildInterceptors(isDebugging)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(S::class.java)
    }

    fun buildInterceptors(isDebugging: Boolean): OkHttpClient {
        val loggerInterceptor = getLoggerInterceptor(isDebugging)
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            addInterceptor(loggerInterceptor)
            addInterceptor(ApiKeyInterceptor(API_KEY))
        }.build()
    }

    private fun getLoggerInterceptor(isDebugging: Boolean): HttpLoggingInterceptor {
        val level = if (isDebugging)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().apply { this.level = level }
    }
}