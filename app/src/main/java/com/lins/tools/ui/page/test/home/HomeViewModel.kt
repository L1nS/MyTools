package com.lins.tools.ui.page.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lins.tools.ui.widgets.TabTitle

class HomeViewModel : ViewModel() {
    var viewStates by mutableStateOf(HomeViewState())
        private set

    init {
        viewStates = viewStates.copy(
            titles = listOf(
                TabTitle(101, "广场"),
                TabTitle(102, "推荐"),
                TabTitle(103, "问答")
            )
        )
    }
}

data class HomeViewState(val titles: List<TabTitle> = emptyList())