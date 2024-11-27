package com.aitorsansal.scrollabledetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aitorsansal.scrollabledetail.data.dataRepository
import com.aitorsansal.scrollabledetail.ui.screens.MonstersPager


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues : PaddingValues = PaddingValues(0.dp)){

    NavHost(
        navController = navController,
        startDestination = MHWorldListNav,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<MHWorldListNav>{
            MonstersPager(monsterList = dataRepository.MHWorldData, innerPadding = paddingValues)
        }
        composable<MHRiseListNav>{
            MonstersPager(monsterList = dataRepository.MHRiseData, innerPadding = paddingValues)
        }
        composable<MH4UListNav>{
            MonstersPager(monsterList = dataRepository.MH4UData, innerPadding = paddingValues)
        }
    }
}