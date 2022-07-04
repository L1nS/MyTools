package com.lins.tools.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.lins.tools.ui.page.main.home.HomePage
import com.lins.tools.ui.page.main.home.life.YearLifePage
import com.lins.tools.ui.page.main.person.PersonPage
import com.lins.tools.ui.page.test.home.TestHomePage
import com.lins.tools.ui.widgets.BottomNavBarView

@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            when (currentDestination?.route) {
                RouteName.HOME -> BottomNavBarView(navCtrl)
                RouteName.PERSON -> BottomNavBarView(navCtrl)
            }

        },

        content = {
            NavHost(
                modifier = Modifier.background(Color.White),
                navController = navCtrl,
                startDestination = RouteName.HOME
            ) {
                composable(RouteName.HOME) {
                    HomePage(navCtrl)
//                    TestHomePage()
                }
                composable(RouteName.PERSON) {
                    PersonPage()
                }
                composable(RouteName.YEAR_LIFE) {
                    YearLifePage(navCtrl)
                }
            }
        }
    )
}