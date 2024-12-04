package com.aitorsansal.rps.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.model.PlayerEndData
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenDestination

@Serializable
object PreferencesScreenDestination

@Serializable
data class GameScreenDestination(var playMode : PlayMode, var playerName : String, var maxGames : Int)
//object GameScreenDestination

@Serializable
object InstructionsScreenDestination

@Serializable
data class EndGameScreenDestination(var playerName: String, var roundsWon : Int, var roundsLost : Int, var enemiesWon : Int)

data class DrawerOption<T:Any>(val route:T, val nonSelectedIcon: ImageVector, val selectedIcon: ImageVector, val title:String, val showBadge:Boolean = false, val badgeIcon:ImageVector = Icons.Default.Star)

val drawerOptions = listOf(
    DrawerOption(HomeScreenDestination, Icons.Outlined.Home, Icons.Filled.Home, "Home Screen"),
    DrawerOption(GameScreenDestination(PlayMode.Normal, "", 0), Icons.Outlined.PlayArrow, Icons.Filled.PlayArrow, "Game Screen"),
    DrawerOption(PreferencesScreenDestination, Icons.Outlined.Settings, Icons.Filled.Settings, "Preferences", showBadge = true),
    DrawerOption(InstructionsScreenDestination, Icons.Outlined.Book, Icons.Filled.Book, "Instructions"),
)