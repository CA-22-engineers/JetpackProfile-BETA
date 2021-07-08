package com.beta9.jetpackprofile_beta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beta9.jetpackprofile_beta.ui.theme.TextColor272727
import com.beta9.jetpackprofile_beta.ui.theme.TextColor363636
import com.beta9.jetpackprofile_beta.ui.theme.TextColor585858
import com.beta9.jetpackprofile_beta.ui.theme.TwitterThemeBlue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun MainUi() {
    Scaffold(
        topBar = { TopBar() },
        content = {},
    )
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TopBar() {
    Column(
        modifier = Modifier
//            .background(DarkMode)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.uma2),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Rounded.ArrowBack, modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start), contentDescription = null, tint = Color.White
                )
                Icon(
                    Icons.Rounded.Menu, modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End), contentDescription = null, tint = Color.White
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.uma1),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(horizontal = 16.dp)
                    .offset(y = (-30).dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Rounded.MailOutline, modifier = Modifier.padding(8.dp), contentDescription = null, tint = TwitterThemeBlue)
                Icon(Icons.Rounded.ArrowBack, modifier = Modifier.padding(8.dp), contentDescription = null, tint = TwitterThemeBlue)
                var isFollowing by remember { mutableStateOf(true) }
                Button(
                    onClick = { isFollowing = !isFollowing },
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = TwitterThemeBlue)
                ) {
                    Text(text = if (isFollowing) "フォロー中" else "フォロー", color = Color.White)
                }
            }
        }
//    }
//
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ウマ娘プロジェクト公式アカウント", color = TextColor272727, fontSize = 24.sp)
            Text(text = "@uma_musu", color = TextColor585858, fontSize = 14.sp)
            Text(
                text = "ウマ娘プロジェクトの公式アカウントです。ゲームやCD、イベント新情報などをお届けしてまいります！ \n 公式ハッシュタグ → #ウマ娘",
                color = TextColor363636,
                fontSize = 14.sp,
                modifier = Modifier.padding(6.dp)
            )
            Row {
                Text(text = "umamusume.jp", color = Color.Blue, fontSize = 14.sp)
                Text(text = "7月1日から使用しています", color = TextColor585858, fontSize = 14.sp)
            }
            Row {
                Text(text = "16 フォロー", color = TextColor585858, fontSize = 14.sp)
                Text(text = "1億 フォロワー", color = TextColor585858, fontSize = 14.sp)
            }

            Text(text = "TVアニメ『ウマ娘 プリティーダービー Season 2』", color = TextColor585858, fontSize = 14.sp, modifier = Modifier.padding(8.dp))
        }

        val pagerState = rememberPagerState(pageCount = 4)
        val pages = listOf("ツイート", "ツイート&リプライ", "メディア", "いいね")
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .background(Color.White)
                )
            }
        ) {
            // Add tabs for all of our pages
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        //FIXME
                        GlobalScope.launch { pagerState.scrollToPage(index) }
                    },
                )
            }
        }

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            val sampleList = List(10) { "$it" }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(sampleList) {
                    Text(text = "page: ${pagerState.currentPage} \n sample tweet $it", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
