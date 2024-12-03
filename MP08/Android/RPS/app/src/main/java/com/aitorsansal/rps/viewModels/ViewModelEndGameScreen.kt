package com.aitorsansal.rps.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.aitorsansal.rps.navigation.EndGameScreenDestination

class ViewModelEndGameScreen(savedStateHandle: SavedStateHandle) : ViewModel(){

    var state = EndGameState()

    init {
        var params = savedStateHandle.toRoute<EndGameScreenDestination>()
        state = state.copy(playerName = params.playerName, wonRounds = params.roundsWon, lostRounds = params.roundsLost, enemiesDefeated = params.enemiesWon)
    }
}

data class EndGameState(
    val playerName : String = "",
    val wonRounds : Int = 0,
    val lostRounds : Int = 0,
    val enemiesDefeated : Int = 0
)