package com.lins.tools.utils

import androidx.navigation.NavHostController

fun NavHostController.back() {
    navigateUp()
}