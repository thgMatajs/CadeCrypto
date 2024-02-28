package com.gentalha.cadecrypto.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.gentalha.cadecrypto.ui.theme.BlueLight
import com.gentalha.cadecrypto.ui.theme.DarkBlue

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = DarkBlue,
            selectedIconColor = BlueLight,
            unselectedIconColor = Color.LightGray
        )
    )
}
