package com.gentalha.cadecrypto.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.cadecrypto.data.CoinRepository
import com.gentalha.cadecrypto.presentation.model.ExchangeModel
import com.gentalha.cadecrypto.presentation.model.toEntity
import com.gentalha.cadecrypto.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<ExchangeUiState>(
        ExchangeUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchExchanges()
    }

    private fun fetchExchanges() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {

            val exchangesFlow = repository.getExchanges()
            val iconsFlow = repository.getExchangeIcon()

            exchangesFlow
                .zip(iconsFlow) { exchanges, icons ->
                    exchanges.map { exchange ->
                        val icon = icons.firstOrNull { icon ->
                            icon.exchangeId == exchange.exchangeId
                        }
                        exchange.toModel(icon?.url ?: "")
                    }
                }
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { ExchangeUiState.Loading } }
                .catch { error ->
                    _uiState.update {
                        ExchangeUiState.Failure(error)
                    }
                }
                .collect { exchanges ->
                    _uiState.update {
                        if (exchanges.isEmpty())
                            ExchangeUiState.Empty
                        else
                            ExchangeUiState.Success(exchanges)
                    }
                }
        }
    }

    fun filterExchangesBy(name: String) {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.getExchangesBy(name)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _uiState.update { ExchangeUiState.Loading }
                }
                .catch { error ->
                    _uiState.update { ExchangeUiState.Failure(error) }
                }
                .collectLatest { exchanges ->
                    if (exchanges.isEmpty()) {
                        _uiState.update { ExchangeUiState.SearchEmpty }
                    } else {
                        _uiState.update { ExchangeUiState.Success(exchanges.map { it.toModel(it.icon) }) }
                    }
                }
        }
    }

    fun updateFavorite(exchange: ExchangeModel) {
        viewModelScope.launch {
            // TODO: criar UiState para favoritar para mostrar uma snack bar
            repository.addFavorite(exchange.toEntity()).runCatching {
                println("THG_update -> runCatching state loading")
            }.onSuccess {
                println("THG_update -> onSuccess")
            }.onFailure {
                println("THG_update -> onFailure : ${it.message}")
            }
        }
    }
}
