package com.aitorsansal.monsterhunterapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.ui.screens.MonsterInformationScreen
import com.aitorsansal.monsterhunterapp.ui.screens.MonsterList


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues : PaddingValues, scrollState : LazyGridState){

    NavHost(
        navController = navController,
        startDestination = MonsterHunterWorld,
        modifier = Modifier.padding(paddingValues)
    ){
        MonsterHunterWorld(navController)
    }
}

fun NavGraphBuilder.MonsterHunterWorld(navController: NavHostController){
    navigation<MonsterHunterWorld>(startDestination = MHWorldList){
        composable<MHWorldList>{
            MonsterList(dataRepository.MHWorldData, onClickElement = {
                navController.navigate(MonsterInformation(it))
            })
        }
        composable<MonsterInformation>{
            val id = it.toRoute<MonsterInformation>()
            MonsterInformationScreen(id.id)
        }
    }
}
fun NavGraphBuilder.MonsterHunterRise(navController: NavHostController){
    navigation<MonsterHunterRise>(startDestination = MHRiseList){
        composable<MHRiseList>{
            MonsterList(dataRepository.MHRiseData, onClickElement = {
                navController.navigate(MonsterInformation(it))
            })
        }
        composable<MonsterInformation>{
            val id = it.toRoute<MonsterInformation>()
            MonsterInformationScreen(id.id)
        }
    }
}
fun NavGraphBuilder.MonsterHunter4Ultimate(navController: NavHostController){
    navigation<MonsterHunter4Ultimate>(startDestination = MH4UList){
        composable<MH4UList>{
            MonsterList(dataRepository.MH4UData, onClickElement = {
                navController.navigate(MonsterInformation(it))
            })
        }
        composable<MonsterInformation>{
            val id = it.toRoute<MonsterInformation>()
            MonsterInformationScreen(id.id)
        }
    }
}
fun NavGraphBuilder.MonsterHunterWilds(navController: NavHostController){
    navigation<MonsterHunterWilds>(startDestination = MHWildsList){
        composable<MHWildsList>{
            MonsterList(dataRepository.MHWildsData, onClickElement = {
                navController.navigate(MonsterInformation(it))
            })
        }
        composable<MonsterInformation>{
            val id = it.toRoute<MonsterInformation>()
            MonsterInformationScreen(id.id)
        }
    }
}