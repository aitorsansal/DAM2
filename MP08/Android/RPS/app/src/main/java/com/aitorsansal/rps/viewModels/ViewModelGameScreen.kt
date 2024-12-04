package com.aitorsansal.rps.viewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.aitorsansal.rps.model.GameOptions
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.model.PlayerEndData
import com.aitorsansal.rps.model.Results
import com.aitorsansal.rps.navigation.EndGameScreenDestination
import com.aitorsansal.rps.navigation.GameScreenDestination
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class ViewModelGameScreen(savedStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(GameScreenState())
        private set

    val navigationEvent = MutableSharedFlow<EndGameScreenDestination>()
    init {
        var params = savedStateHandle.toRoute<GameScreenDestination>()
        state = state.copy(playMode = params.playMode, playerName = params.playerName, gamesToWin = params.maxGames)
        startNewRound()
    }
    var playerData : PlayerEndData = PlayerEndData(state.playerName, 0,0,0)

    fun selectOption(selOption : GameOptions){
        var enemySelection = getEnemySelection()
        state = state.copy(playerSelection = selOption, enemySelection = enemySelection)
        var res = getResult(selOption, enemySelection)
        when (res){
            Results.Win -> { state = state.copy(currentPlayerPoints = state.currentPlayerPoints + 1,
                currentResult = "${state.currentPlayerPoints+1}-${state.currentEnemyPoints}")
                playerData.wonRounds++
            }

            Results.Loose -> {state = state.copy(currentEnemyPoints = state.currentEnemyPoints + 1,
                currentResult = "${state.currentPlayerPoints}-${state.currentEnemyPoints+1}")
                playerData.lostRounds++
            }

            Results.Draw -> state = state
        }

        if(state.currentPlayerPoints == state.gamesToWin) {
            playerData.wonEnemies++
            startNewRound()
        }
        else if(state.currentEnemyPoints == state.gamesToWin){
            navigateToEndScreen()
        }
    }

    private fun navigateToEndScreen(){
        viewModelScope.launch{
            val action = EndGameScreenDestination(playerName = playerData.name, roundsWon = playerData.wonRounds, roundsLost = playerData.lostRounds, enemiesWon = playerData.wonEnemies)
            navigationEvent.emit(action)
        }
    }

    private fun startNewRound(){
        val faker = faker {}
        val enemyName = faker.name.firstName()
        val round = state.round + 1
        state = state.copy(round = round, currentRound = "Round $round", enemyName = enemyName,
            currentPlayerPoints = 0, currentEnemyPoints = 0, currentResult = "0-0",
            enemySelection = null, playerSelection = null)
    }

    private fun getResult(playerOption: GameOptions, enemyOption: GameOptions) : Results{
        when (playerOption){
            GameOptions.Rock -> return when (enemyOption){
                GameOptions.Paper, GameOptions.Spock -> Results.Loose
                GameOptions.Scissors, GameOptions.Lizard -> Results.Win
                GameOptions.Rock -> Results.Draw
            }
            GameOptions.Paper -> return when (enemyOption){
                GameOptions.Scissors, GameOptions.Lizard -> Results.Loose
                GameOptions.Spock, GameOptions.Rock -> Results.Win
                GameOptions.Paper -> Results.Draw
            }
            GameOptions.Scissors -> return when (enemyOption){
                GameOptions.Rock, GameOptions.Spock -> Results.Loose
                GameOptions.Paper, GameOptions.Lizard -> Results.Win
                GameOptions.Scissors -> Results.Draw
            }
            GameOptions.Lizard -> return when (enemyOption){
                GameOptions.Rock, GameOptions.Scissors -> Results.Loose
                GameOptions.Paper, GameOptions.Spock -> Results.Win
                GameOptions.Lizard -> Results.Draw
            }
            GameOptions.Spock -> return when (enemyOption){
                GameOptions.Paper, GameOptions.Lizard -> Results.Loose
                GameOptions.Scissors, GameOptions.Rock -> Results.Win
                GameOptions.Spock -> Results.Draw
            }
        }
    }

    fun getEnemySelection() : GameOptions{

        if(state.playMode == PlayMode.Extended)
            return GameOptions.entries.random()
        else{
            var entries = listOf(GameOptions.Paper, GameOptions.Rock, GameOptions.Scissors)
            return entries.random()
        }
    }
}

data class GameScreenState(
    val playMode : PlayMode = PlayMode.Normal,
    val playerName : String = "",
    val enemyName : String = "",
    val playerSelection : GameOptions? = null,
    val enemySelection: GameOptions? = null,
    val round : Int = 0,
    val currentRound : String = "Round 1",
    val currentPlayerPoints : Int = 0,
    val currentEnemyPoints : Int = 0,
    val currentResult : String = "0-0",
    val gamesToWin : Int = 0
)