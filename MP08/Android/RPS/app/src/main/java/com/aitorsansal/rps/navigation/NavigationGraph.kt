package com.aitorsansal.rps.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aitorsansal.rps.data.Preferences
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.ui.screens.EndGameScreen
import com.aitorsansal.rps.ui.screens.GameScreen
import com.aitorsansal.rps.ui.screens.HomeScreen
import com.aitorsansal.rps.ui.screens.InstructionsScreen
import com.aitorsansal.rps.ui.screens.PreferencesScreen


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues: PaddingValues = PaddingValues(0.dp)){
    var pref = Preferences.getInstance(LocalContext.current)
    var gameMode = pref.getPlayMode.collectAsState(initial = PlayMode.Normal).value
    var playerName = pref.getPlayerName.collectAsState(initial = "").value
    NavHost(navController = navController, modifier = Modifier.padding(paddingValues), startDestination = HomeScreenDestination){

        composable<HomeScreenDestination> {
            HomeScreen(onPlayClick = { navController.navigate(GameScreenDestination(playMode = gameMode, playerName)){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }},
                onSettingsClick = {navController.navigate(PreferencesScreenDestination){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                } })
        }

        composable<GameScreenDestination>{
            val argument = it.toRoute<GameScreenDestination>()
            GameScreen(navController)
        }

        composable<PreferencesScreenDestination>{
            PreferencesScreen()
        }

        composable<EndGameScreenDestination>{
            val argument = it.toRoute<EndGameScreenDestination>()
            EndGameScreen()
        }

        composable<InstructionsScreenDestination>{
            InstructionsScreen()
        }
    }
}