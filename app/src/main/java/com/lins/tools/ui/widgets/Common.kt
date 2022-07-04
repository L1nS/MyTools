package com.lins.tools.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lins.tools.R
import com.lins.tools.ui.page.common.BottomNavRoute
import com.lins.tools.ui.theme.SearchBarHeight
import com.lins.tools.ui.theme.TitleTextSize
import com.lins.tools.ui.theme.ToolbarHeight
import com.zj.wanandroid.theme.AppTheme

@Composable
fun BottomNavBarView(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Person
    )

    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->

            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(screen.stringId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
                    println("BottomNavView 当前路由 = ${currentDestination?.hierarchy?.toList()}")
                    println("当前路由栈 ===>${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )

        }
    }
}

data class TabTitle(
    var id: Int,
    var text: String,
    var cachePosition: Int = 0,
    var select: Boolean = false
)

@Composable
fun TextTabBar(
    index: Int,
    tabText: List<TabTitle>,
    bgColor: Color = AppTheme.colors.themeUi,
    contentColor: Color = Color.White,
    contentAlign: Alignment = Alignment.Center,
    onTabSelect: ((index: Int) -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(bgColor)
    ) {
        Row(
            modifier = Modifier.align(contentAlign)
        ) {
            tabText.forEachIndexed { i, tabTitle ->
                Text(
                    text = tabTitle.text,
                    fontSize = if (index == i) 20.sp else 15.sp,
                    color = contentColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(10.dp)
                        .clickable {
                            onTabSelect?.invoke(i)
                        },
                    fontWeight = if (index == i) FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarHeight)
            .background(AppTheme.colors.themeUi)
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .weight(1f)
                .height(30.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable {
                    onSearchClick.invoke()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "搜索",
                tint = AppTheme.colors.themeUi,
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "搜索关键词以空格形式隔开",
                    fontSize = 13.sp,
                    color = AppTheme.colors.textSecondary
                )
            }
        }

    }
}

@Composable
fun NormalToolbar(
    title: String = "",
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToolbarHeight)
            .background(AppTheme.colors.themeUi)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "返回",
            tint = Color.White,
            modifier = Modifier
                .padding(start = 9.dp)
                .size(30.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    onBack.invoke()
                }
        )

        Text(text = title,
        fontSize = TitleTextSize,
        color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}