package com.gentalha.cadecrypto.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchanges")
data class ExchangeEntity(
    val name: String,
    @PrimaryKey(autoGenerate = false) val exchangeId: String,
    val volumeDayUsd: Int,
    val icon: String,
    var isFavorite: Boolean
)
