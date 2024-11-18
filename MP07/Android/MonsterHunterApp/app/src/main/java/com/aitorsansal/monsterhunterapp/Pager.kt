package com.aitorsansal.monsterhunterapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.navigation.navigationCategories
import com.aitorsansal.monsterhunterapp.ui.screens.MonsterList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPager(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().height(60.dp)){
                Image(
                    painter = painterResource(R.drawable.pergamin_background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                TopAppBar(
                    title = {Text("Pager")},
                    navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Open/Close Drawer"
                                )
                            }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black.copy(alpha = .0f),
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        }) { pad ->
        Box {
            Image(painter = painterResource(R.drawable.pergamin_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize())
            AppPagerContainer(pad)

        }
    }
}

@Composable
fun AppPagerContainer(innerPadding: PaddingValues) {
    val pagerState: PagerState = rememberPagerState{ 3 }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        TabRow(selectedTabIndex = pagerState.currentPage, modifier = Modifier.background(color = Color.Transparent)) {
            repeat(pagerState.pageCount){ pg ->
                Box {
                    Tab(selected = pg == pagerState.currentPage,
                        modifier = Modifier.background(color = Color.Transparent),
                        onClick = {coroutineScope.launch{
                            pagerState.animateScrollToPage(pg)
                        }},
                        content = {Text(navigationCategories[pg].title)})

                }
            }
        }
        HorizontalPager(state = pagerState,
            beyondViewportPageCount = 3,

            ) { page ->
            when(page){
                0 -> MonsterList(dataRepository.MHWorldData, showBackground = false)
                1 -> MonsterList(dataRepository.MHRiseData, showBackground = false)
                2 -> MonsterList(dataRepository.MH4UData, showBackground = false)
            }
        }

    }
}