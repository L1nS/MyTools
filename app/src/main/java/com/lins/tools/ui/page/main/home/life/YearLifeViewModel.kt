package com.lins.tools.ui.page.main.home.life

import androidx.lifecycle.ViewModel
import com.lins.tools.utils.getDay
import com.lins.tools.utils.getMonth
import com.lins.tools.utils.getYear
import java.util.*

class YearLifeViewModel : ViewModel() {

    fun getRemainDay(): Int {
        val nowDay = Calendar.getInstance()
        val lastDay = Calendar.getInstance()
        lastDay.set(getYear(), 11, 31)
        nowDay.set(getYear(), getMonth() - 1, getDay())
        return lastDay.get(Calendar.DAY_OF_YEAR) - nowDay.get(Calendar.DAY_OF_YEAR)
    }

    fun getRemainDayPercent(): Int {
        val nowDay = Calendar.getInstance()
        val lastDay = Calendar.getInstance()
        lastDay.set(getYear(), 11, 31)
        nowDay.set(getYear(), getMonth() - 1, getDay())
        return nowDay.get(Calendar.DAY_OF_YEAR) * 100 / lastDay.get(Calendar.DAY_OF_YEAR)
    }

}