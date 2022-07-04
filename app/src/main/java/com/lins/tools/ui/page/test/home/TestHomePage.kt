package com.lins.tools.ui.page.test.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lins.tools.ui.page.main.home.HomeViewModel
import com.lins.tools.ui.page.test.home.question.QuestionPage
import com.lins.tools.ui.page.test.home.recommend.RecommendPage
import com.lins.tools.ui.page.test.home.square.SquarePage
import com.lins.tools.ui.widgets.SearchBar
import com.lins.tools.ui.widgets.TextTabBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TestHomePage(
    viewModel: HomeViewModel = HomeViewModel()
) {
    val titles = viewModel.viewStates.titles
    val scopeState = rememberCoroutineScope()

    Column {
        val pagerState = rememberPagerState(
            initialPage = 0
        )

        TextTabBar(
            index = pagerState.currentPage,
            titles,
            onTabSelect = { index ->
                scopeState.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )

        SearchBar(onSearchClick = {
            println("onSearchClick")
        })

        HorizontalPager(count = titles.size, state = pagerState) { page ->
            when (page) {
                0 -> SquarePage()
                1 -> RecommendPage()
                2 -> QuestionPage()
            }
        }
    }
}