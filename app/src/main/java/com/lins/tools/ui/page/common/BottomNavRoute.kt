package com.lins.tools.ui.page.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.lins.tools.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {

    object Home : BottomNavRoute(RouteName.HOME, R.string.nav_home, Icons.Default.Home)
    object Person : BottomNavRoute(RouteName.PERSON, R.string.nav_person, Icons.Default.Person)
}
