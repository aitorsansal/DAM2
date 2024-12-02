package com.aitorsansal.rps.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aitorsansal.rps.ui.screens.EndGameScreen
import com.aitorsansal.rps.ui.screens.GameScreen
import com.aitorsansal.rps.ui.screens.HomeScreen
import com.aitorsansal.rps.ui.screens.InstructionsScreen
import com.aitorsansal.rps.ui.screens.PreferencesScreen


@Composable
fun NavigationGraph(navController : NavHostController, paddingValues: PaddingValues = PaddingValues(0.dp)){
    NavHost(navController = navController, modifier = Modifier.padding(paddingValues), startDestination = HomeScreenDestination){

        composable<HomeScreenDestination> {
            HomeScreen()
        }

        composable<GameScreenDestination>{
            val argument = it.toRoute<GameScreenDestination>()
            GameScreen()
        }

        composable<PreferencesScreenDestination>{
            PreferencesScreen()
        }

        composable<EndGameScreenDestination>{
            EndGameScreen()
        }

        composable<InstructionsScreenDestination>{
            InstructionsScreen()
        }
    }
}