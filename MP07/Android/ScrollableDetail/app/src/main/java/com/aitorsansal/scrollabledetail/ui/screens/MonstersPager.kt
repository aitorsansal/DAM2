package com.aitorsansal.scrollabledetail.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aitorsansal.scrollabledetail.model.Monster
import kotlinx.coroutines.launch


@Composable
fun MonstersPager(innerPadding : PaddingValues, monsterList: List<Monster>) {
    val pagerState: PagerState = rememberPagerState{ monsterList.count() }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.background(color = Color.Transparent)
        ) {
            repeat(pagerState.pageCount) {
                Tab(selected = it == pagerState.currentPage,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(it) } },
                    content = { Text(monsterList[it].name) })
            }
        }
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 3,

            ) {
            MonsterInformationScreen(monsterList[it].id.toString())
        }
    }

}