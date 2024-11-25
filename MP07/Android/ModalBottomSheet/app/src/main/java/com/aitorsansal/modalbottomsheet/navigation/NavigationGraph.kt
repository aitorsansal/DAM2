package com.aitorsansal.modalbottomsheet.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aitorsansal.modalbottomsheet.data.dataRepository
import com.aitorsansal.modalbottomsheet.ui.screens.HorizontalComposableList
import com.aitorsansal.modalbottomsheet.ui.screens.MonsterInformationScreen


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues : PaddingValues = PaddingValues(0.dp), elementWidth : Dp = 500.dp){

    NavHost(
        navController = navController,
        startDestination = MHWorldListNav,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<MHWorldListNav>{
            HorizontalComposableList(data = dataRepository.MHWorldData, {navController.navigate(
                MonsterInformationNav(it))}, elementWidth = elementWidth)
        }
        composable<MHRiseListNav>{
            HorizontalComposableList(data = dataRepository.MHRiseData, {navController.navigate(
                MonsterInformationNav(it))}, elementWidth = elementWidth)
        }
        composable<MH4UListNav>{
            HorizontalComposableList(data = dataRepository.MH4UData, {navController.navigate(
                MonsterInformationNav(it))}, elementWidth = elementWidth)
        }

        composable<MonsterInformationNav>{
            val argument = it.toRoute<MonsterInformationNav>()
            MonsterInformationScreen(argument.monsterId)
        }
    }
}