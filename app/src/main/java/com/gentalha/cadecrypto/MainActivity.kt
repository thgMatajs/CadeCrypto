package com.gentalha.cadecrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.gentalha.cadecrypto.presentation.ExchangeViewModel
import com.gentalha.cadecrypto.presentation.SplashScreenViewModel
import com.gentalha.cadecrypto.ui.components.SearchTextBar
import com.gentalha.cadecrypto.ui.navigation.TabNavigationItem
import com.gentalha.cadecrypto.ui.tab.favorite.FavoriteTab
import com.gentalha.cadecrypto.ui.tab.home.HomeTab
import com.gentalha.cadecrypto.ui.theme.BlueGray
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
    val home = HomeTab(viewModel, viewModel::updateFavorite)
    TabNavigator(home) {
        Scaffold(
            topBar = {
                SearchTextBar(
                    onValueChange = viewModel::filterExchangesBy,
                    onKeyBoardClickAction = viewModel::filterExchangesBy,
                    onClearClick = { viewModel.filterExchangesBy("") }
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .background(DarkBlue)
                        .clip(
                            RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .height(60.dp),
                    containerColor = BlueGray
                ) {
                    TabNavigationItem(tab = home)
                    TabNavigationItem(tab = FavoriteTab())
                }
            }
        ) {
            Box(
                Modifier
                    .padding(it)
                    .background(DarkBlue)
            ) {
                CurrentTab()
            }

        }
    }
}

