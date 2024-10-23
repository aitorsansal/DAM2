package com.aitorsansal.monsterhunterapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.model.Monster
import com.aitorsansal.monsterhunterapp.ui.screens.CustomGridList
import com.aitorsansal.monsterhunterapp.ui.screens.MonsterInformationScreen


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues : PaddingValues, scrollState : LazyGridState){

    NavHost(
        navController = navController,
        startDestination = MainScreen,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<MainScreen>{
            CustomGridList(gridState = scrollState, onClickElement ={navController.navigate(MonsterInformation(it))})
        }

        composable<MonsterInformation>{ backStackEntry ->
            val element = backStackEntry.toRoute<MonsterInformation>()
            MonsterInformationScreen(element.id)
        }
    }
}