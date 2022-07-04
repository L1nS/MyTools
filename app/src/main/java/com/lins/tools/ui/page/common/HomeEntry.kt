package com.lins.tools.ui.page.common

import androidx.compose.runtime.*
import com.lins.tools.ui.page.splash.SplashPage
import com.zj.wanandroid.theme.AppTheme

@Composable
fun HomeEntry() {
    var isSplash by remember {
        mutableStateOf(true)
    }
    AppTheme{
        if (isSplash) {
            SplashPage { isSplash = false }
        } else {
            AppScaffold()
        }
    }
}