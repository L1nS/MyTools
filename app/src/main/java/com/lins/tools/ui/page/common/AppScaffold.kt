package com.lins.tools.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.lins.tools.R
import com.lins.tools.ui.page.main.home.HomePage
import com.lins.tools.ui.page.main.home.life.YearLifePage
import com.lins.tools.ui.page.main.person.PersonPage
import com.lins.tools.ui.widgets.AppSnackBar
import com.lins.tools.ui.widgets.SNACK_ERROR
import com.lins.tools.ui.widgets.popupSnackBar
import com.lins.tools.utils.back
import com.zj.wanandroid.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var isHomePage by remember {
        mutableStateOf(true)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            if (isHomePage) {
                AppToolBar(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                )
            }
        },
        /*bottomBar = {
            when (currentDestination?.route) {
                RouteName.HOME -> BottomNavBarView(navCtrl)
                RouteName.PERSON -> BottomNavBarView(navCtrl)
            }

        },*/

        content = {
            NavHost(
                modifier = Modifier.background(Color.White),
                navController = navCtrl,
                startDestination = RouteName.HOME
            ) {
                composable(RouteName.HOME) {
                    isHomePage = true
                    println("RouteName HOME")
                    HomePage(navCtrl)
                }
                composable(RouteName.PERSON) {
                }
                composable(RouteName.YEAR_LIFE) {
                    isHomePage = false
                    println("RouteName YEAR_LIFE")
                    YearLifePage(navCtrl)
                }
            }
        },

        snackbarHost = {
            SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
                println("actionLabel = ${data.actionLabel}")
                AppSnackBar(data = data)
            }
        },
        drawerGesturesEnabled = isHomePage,
        drawerContent = {
            PersonPage()
        }
    )
}

@Composable
fun AppToolBar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {
    TopAppBar(
        backgroundColor = AppTheme.colors.themeUi,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "菜单",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {
                popupSnackBar(coroutineScope, scaffoldState, label = SNACK_ERROR, "分享")
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "分享",
                    tint = Color.White
                )
            }
        }
    )
}