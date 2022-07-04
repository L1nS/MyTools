package com.lins.tools.ui.page.main.home.life

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.lins.tools.ui.widgets.NormalToolbar
import com.lins.tools.utils.back
import com.lins.tools.utils.getDay
import com.lins.tools.utils.getMonth
import com.lins.tools.utils.getWeek
import com.zj.wanandroid.theme.AppTheme

@Composable
fun YearLifePage(
    navCtrl: NavHostController,
    viewModel: YearLifeViewModel = YearLifeViewModel()
) {

    val imageUrl = "https://api.yimian.xyz/img?type=wallpaper"
    val nowPercent = viewModel.getRemainDayPercent()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        NormalToolbar(title = "Title", onBack = {
            navCtrl.back()
        })

        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(400.dp)
                .border(
                    shape = RoundedCornerShape(10.dp),
                    width = 1.dp,
                    color = Color.Gray
                )
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${getMonth()}月${getDay()}日 ${getWeek()}"
                    + " 离全年结束还有${viewModel.getRemainDay()}天",
            modifier = Modifier.padding(start = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "", modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(nowPercent.toFloat())
                        .background(color = AppTheme.colors.themeUi)
                )
                Text(
                    text = "", modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight((100 - nowPercent).toFloat())
                        .border(width = 0.5.dp, color = Color.LightGray)
                )
            }
            Text(
                text = "${nowPercent}%",
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }

}