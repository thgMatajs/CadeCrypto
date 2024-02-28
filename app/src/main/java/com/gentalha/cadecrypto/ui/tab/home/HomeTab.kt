package com.gentalha.cadecrypto.ui.tab.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gentalha.cadecrypto.presentation.ExchangeViewModel
import com.gentalha.cadecrypto.ui.components.SearchTextBar
import com.gentalha.cadecrypto.ui.screens.ExchangesScreen

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: ExchangeViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchTextBar(
                onValueChange = viewModel::filterExchangesBy,
                onKeyBoardClickAction = viewModel::filterExchangesBy,
                onClearClick = { viewModel.filterExchangesBy("") }
            )
            ExchangesScreen(uiState = uiState, onFavoriteOnClick = viewModel::updateFavorite)
        }
    }
}
