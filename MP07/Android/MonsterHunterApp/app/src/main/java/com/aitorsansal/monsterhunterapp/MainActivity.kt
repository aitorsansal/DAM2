package com.aitorsansal.monsterhunterapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.navigation.MainScreen
import com.aitorsansal.monsterhunterapp.navigation.NavigationGraph
import com.aitorsansal.monsterhunterapp.ui.theme.MonsterHunterAppTheme
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.aitorsansal.monsterhunterapp.data.GameData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterHunterAppTheme {
                val navController = rememberNavController()
                val currentRoute by navController.currentBackStackEntryAsState()
                val destination = currentRoute?.destination
                fakeRepository.obtainData()
                val gridState = rememberLazyGridState()

                // State for the Drawer
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                // Coroutine scope for drawer operations
                val coroutineScope = rememberCoroutineScope()
                var selectedGame by remember{mutableStateOf(GameData.games["MHWorld"])}

                // ModalDrawer
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        // Drawer content
                        Sidebar(drawerState = drawerState, coroutineScope = coroutineScope, onGameSelected = {
                            game -> selectedGame = GameData.games[game]
                        })
                    }
                ) {
                    App(drawerState = drawerState, coroutineScope = coroutineScope, navController = navController, gridState = gridState, gameSelected = selectedGame)
                }
            }
        }
    }


    @Composable
    fun Sidebar(drawerState: DrawerState, coroutineScope: CoroutineScope, onGameSelected: (String) -> Unit) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Select Game", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            GameData.games.forEach(){ (id,name) ->
                Button(onClick = {onGameSelected(id)
                                toggleDrawer(drawerState = drawerState, coroutineScope = coroutineScope)
                }) {
                    Text(name)
                }
            }
            // Add more buttons for other games...
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App(drawerState: DrawerState, coroutineScope: CoroutineScope, navController : NavHostController,gridState : LazyGridState,gameSelected : String? = "Monster Hunter World")
    {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = gameSelected ?: "No game selected") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Open or close the drawer based on the current state
                                toggleDrawer(drawerState = drawerState, coroutineScope = coroutineScope)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open/Close Drawer"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavigationGraph(navController, paddingValues = paddingValues, scrollState = gridState)
        }
    }


    private fun toggleDrawer(drawerState: DrawerState, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            if (drawerState.isOpen) {
                drawerState.close()
            } else {
                drawerState.open()
            }
        }
    }
}