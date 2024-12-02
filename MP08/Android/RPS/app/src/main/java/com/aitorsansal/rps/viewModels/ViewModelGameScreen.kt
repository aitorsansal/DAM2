package com.aitorsansal.rps.viewModels

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.aitorsansal.rps.data.Preferences
import com.aitorsansal.rps.model.GameOptions
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.model.PlayerEndData
import com.aitorsansal.rps.model.Results
import com.aitorsansal.rps.model.toPlayMode
import com.aitorsansal.rps.navigation.GameScreenDestination


class ViewModelGameScreen(savedStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(GameScreenState())
        private set
    init {
        var params = savedStateHandle.toRoute<GameScreenDestination>()
        state = state.copy(playMode = params.gameMode.toPlayMode(), playerName = "", enemyName = "")
    }
    var playerData : PlayerEndData = PlayerEndData(state.playerName, 0,0,0)

    fun SelectOption(selOption : GameOptions){
        var enemySelection = GetEnemySelection()

    }

    private fun GetResult(playerOption: GameOptions, enemyOption: GameOptions) : Results{
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

    fun GetEnemySelection() : GameOptions{

        return GameOptions.entries.random()
    }
}

data class GameScreenState(
    val playMode : PlayMode = PlayMode.Normal,
    val playerName : String = "",
    val enemyName : String = ""
)