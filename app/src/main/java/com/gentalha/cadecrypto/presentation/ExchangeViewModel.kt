package com.gentalha.cadecrypto.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.cadecrypto.data.CoinRepository
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
            repository.getExchanges()
                .flowOn(Dispatchers.IO)
                .onStart {
                    _uiState.update { ExchangeUiState.Loading }
                }
                .catch { error ->
                    println("ERROR: ${error.message}")
                    _uiState.update {
                        ExchangeUiState.Failure(error)
                    }
                }
                .collectLatest { exchanges ->
                    _uiState.update {
                        ExchangeUiState.Success(
                            exchanges.map { it.toModel() }
                        )
                    }
                }
        }
    }
}