package com.gentalha.cadecrypto.ui.tab.favorite

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gentalha.cadecrypto.presentation.viewmodel.FavoriteViewModel
import com.gentalha.cadecrypto.ui.screens.FavoriteScreen

class FavoriteTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Favorite)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "Favorito",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: FavoriteViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()
        FavoriteScreen(uiState, viewModel::updateFavorite)
    }
}
