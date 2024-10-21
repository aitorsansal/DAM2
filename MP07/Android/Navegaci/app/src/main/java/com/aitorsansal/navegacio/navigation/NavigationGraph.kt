package com.aitorsansal.navegacio.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aitorsansal.navegacio.ui.screens.CoinFlipScreen
import com.aitorsansal.navegacio.ui.screens.FirstScreen
import com.aitorsansal.navegacio.ui.screens.OracleResponseScreen
import com.aitorsansal.navegacio.ui.screens.OracleScreen
import com.aitorsansal.navegacio.ui.screens.SelectNumberScreen
import com.aitorsansal.navegacio.ui.screens.ShowNumberScreen

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues = PaddingValues(0.dp)){

    NavHost(
        navController = navController,
        startDestination = StartScreen,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<StartScreen>{
            FirstScreen(onCoinFlipClick = { navController.navigate(CoinFlip)},
                onGetRandomNumberClick = {navController.navigate(SelectNumber)},
                onOracleClick = {navController.navigate(Oracle)})
        }
        composable<CoinFlip>{
            CoinFlipScreen()
        }

        composable<SelectNumber>{
            SelectNumberScreen(onSendClick = {start:Int, end:Int -> navController.navigate(SelectedNumber(start,end)){
                popUpTo(StartScreen) {
                    inclusive = false
                }
            } })
        }

        composable<SelectedNumber>{ backStackEntry ->
            val element = backStackEntry.toRoute<SelectedNumber>()
            ShowNumberScreen(element.start, element.end)
        }

        composable<Oracle>{
            OracleScreen(onSendClick = {navController.navigate(OracleResponse(it)){
                popUpTo(StartScreen) {
                    inclusive = false
                }
            } })
        }

        composable<OracleResponse>{ backStackEntry ->
            val element = backStackEntry.toRoute<OracleResponse>()
            OracleResponseScreen(element.question)
        }
    }
}