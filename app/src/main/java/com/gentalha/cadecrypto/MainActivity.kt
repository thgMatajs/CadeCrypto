package com.gentalha.cadecrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gentalha.cadecrypto.presentation.ExchangeViewModel
import com.gentalha.cadecrypto.presentation.SplashScreenViewModel
import com.gentalha.cadecrypto.ui.components.SearchTextBar
import com.gentalha.cadecrypto.ui.screens.ExchangesScreen
import com.gentalha.cadecrypto.ui.theme.CadeCryptoTheme
import com.gentalha.cadecrypto.ui.theme.DarkBlue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ExchangeViewModel by viewModels()
    private val splashViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
        }
        setContent {
            CadeCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBlue
                ) {
                    App(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: ExchangeViewModel) {

    Scaffold(
        topBar = {
            SearchTextBar(
                onValueChange = viewModel::filterExchangesBy,
                onKeyBoardClickAction = viewModel::filterExchangesBy,
                onClearClick = { viewModel.filterExchangesBy("") }
            )
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .background(DarkBlue)
        ) {
            val uiState by viewModel.uiState.collectAsState()
            ExchangesScreen(
                uiState = uiState,
                onFavoriteOnClick = viewModel::updateFavorite
            )
        }

    }
}
