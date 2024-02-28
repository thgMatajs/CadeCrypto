package com.gentalha.cadecrypto.ui.tab.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gentalha.cadecrypto.presentation.ExchangeViewModel
import com.gentalha.cadecrypto.presentation.model.ExchangeModel
import com.gentalha.cadecrypto.ui.screens.ExchangesScreen

class HomeTab(
    private val viewModel: ExchangeViewModel,
    private val onFavoriteOnClick: (ExchangeModel) -> Unit
) : Tab {
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
        val uiState by viewModel.uiState.collectAsState()
        ExchangesScreen(uiState = uiState, onFavoriteOnClick = onFavoriteOnClick::invoke)
    }
}
