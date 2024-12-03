package com.aitorsansal.rps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.rps.data.Preferences
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.model.toInt
import com.aitorsansal.rps.navigation.EndGameScreenDestination
import com.aitorsansal.rps.navigation.GameScreenDestination
import com.aitorsansal.rps.navigation.HomeScreenDestination
import com.aitorsansal.rps.navigation.NavigationGraph
import com.aitorsansal.rps.navigation.PreferencesScreenDestination
import com.aitorsansal.rps.navigation.drawerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("RestrictedApi")
@Composable
fun CustomNavigationDrawer(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    actualRoute: NavBackStackEntry?,
    actualDestination: NavDestination?
) {
    val context = LocalContext.current
    val preferences = remember { Preferences.getInstance(context) }
    val playMode = preferences.getPlayMode.collectAsState(initial = PlayMode.Normal).value
    val playerName = preferences.getPlayerName.collectAsState(initial = "").value
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                drawerShape = ShapeDefaults.Small, //fa referència a la mida del corner radius
                drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                drawerContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                drawerTonalElevation = 5.dp,
                windowInsets = WindowInsets(left = 24.dp, right = 24.dp, top = 48.dp) // És el padding
            ){
                Icon(painter = painterResource(id = R.drawable.rps_banner),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().aspectRatio(3F),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer (Modifier.height(48.dp))
                HorizontalDivider(
                    modifier = Modifier.height(15.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer (Modifier.height(48.dp))
                drawerOptions.forEach { cat->
                    NavigationDrawerItem  (
                        label = { Text(cat.title) },
                        selected = actualDestination?.hierarchy?.any {it.hasRoute(cat.route::class) } == true,
                        icon = {if (actualDestination?.hierarchy?.any {it.hasRoute(cat.route::class) } == true)
                            Icon(imageVector = cat.selectedIcon, contentDescription = cat.title)
                        else
                            Icon(imageVector = cat.nonSelectedIcon, contentDescription = cat.title)
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            if(cat.route == GameScreenDestination(PlayMode.Normal, "")){
                                navController.navigate(GameScreenDestination(playMode, playerName)){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            else{
                                navController.navigate(cat.route) {
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }  }
                            },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = MaterialTheme.colorScheme.error,
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectedBadgeColor = MaterialTheme.colorScheme.error,
                            selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer),
                        badge = {
                            if (cat.showBadge)
                                Icon(imageVector = cat.badgeIcon, "Opció destacada")
                        },
                        shape = ShapeDefaults.Medium
                    )
                }
            }
        },
        gesturesEnabled = CanOpenDrawer(actualDestination)
    ) {
        Bastida(
            actualRoute = actualRoute,
            actualDestination = actualDestination,
            navController = navController,
            coroutineScope = coroutineScope,
            drawerState = drawerState
        )
    }
}

@SuppressLint("RestrictedApi")
private fun CanOpenDrawer(actualDestination: NavDestination?) = listOf(
    PreferencesScreenDestination::class,
    HomeScreenDestination::class,
    EndGameScreenDestination::class,
).any { actualDestination?.hasRoute(it) ?: true }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bastida(
    actualRoute: NavBackStackEntry?,
    actualDestination: NavDestination?,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rock/Paper/Scissor/Lizard/Spock") },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    if(CanOpenDrawer(actualDestination)) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed)
                                    drawerState.open()
                                else
                                    drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    else{
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                })
        }

    )
    {paddingValues ->
        NavigationGraph(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}