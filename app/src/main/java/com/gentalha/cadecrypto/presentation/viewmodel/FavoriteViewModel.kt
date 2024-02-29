package com.gentalha.cadecrypto.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.cadecrypto.data.CoinRepository
import com.gentalha.cadecrypto.presentation.model.ExchangeModel
import com.gentalha.cadecrypto.presentation.model.toEntity
import com.gentalha.cadecrypto.presentation.model.toModel
import com.gentalha.cadecrypto.presentation.state.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {
    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<FavoriteUiState>(
        FavoriteUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        getFavorites()
    }

    fun getFavorites() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.getFavorites()
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { FavoriteUiState.Loading } }
                .map { exchanges ->
                    exchanges.map { it.toModel() }
                }
                .catch { error ->
                    _uiState.update { FavoriteUiState.Failure(error) }
                }
                .collect { exchanges ->
                    _uiState.update {
                        if (exchanges.isEmpty()) {
                            FavoriteUiState.Empty
                        } else {
                            FavoriteUiState.Success(exchanges)
                        }
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
                getFavorites()
            }.onFailure {
                println("THG_update -> onFailure : ${it.message}")
            }
        }
    }
}
