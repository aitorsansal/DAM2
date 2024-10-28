package com.aitorsansal.monsterhunterapp

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.navigation.NavigationGraph
import com.aitorsansal.monsterhunterapp.ui.theme.MonsterHunterAppTheme
import androidx.navigation.NavHostController
import com.aitorsansal.monsterhunterapp.data.GameData
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunter4Ultimate
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunterRise
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunterWorld
import com.aitorsansal.monsterhunterapp.navigation.NavigationCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val MonsterViewModelProvider = compositionLocalOf<MonsterViewModel>{error("No viewmodel passed")}
var navigationCategories : List<NavigationCategories<out Any>> = listOf()

class MainActivity : ComponentActivity() {

    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterHunterAppTheme {
                navigationCategories = listOf(
                    NavigationCategories<MonsterHunterWorld>(
                        route = MonsterHunterWorld,
                        selectedIcon = ImageVector.vectorResource(R.drawable.mhworld_icon),
                        title = "MHW"
                    ),
                    NavigationCategories<MonsterHunterRise>(
                        route = MonsterHunterRise,
                        selectedIcon = ImageVector.vectorResource(R.drawable.mhrise_icon),
                        title = "MHW"
                    ),
                    NavigationCategories<MonsterHunter4Ultimate>(
                        route = MonsterHunter4Ultimate,
                        selectedIcon = ImageVector.vectorResource(R.drawable.mh4u_icon),
                        title = "MHW"
                    )
                )
                val navController = rememberNavController()
                val currentRoute by navController.currentBackStackEntryAsState()
                val destination = currentRoute?.destination
                dataRepository.obtainData(this)
                val gridState = rememberLazyGridState()

                //viewModel
                val viewModel : MonsterViewModel = viewModel()
                // State for the Drawer
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                // Coroutine scope for drawer operations
                val coroutineScope = rememberCoroutineScope()

                //GameDetection
                var selectedGame by remember{mutableStateOf(GameData.games["MHWorld"])}
                var rememberMonsterData by remember {mutableStateOf(dataRepository.MHWorldData)}
                viewModel.setMonsters(rememberMonsterData)

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
                    CompositionLocalProvider(MonsterViewModelProvider provides viewModel) {
                        App()
                    }
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
    fun App(drawerState: DrawerState, coroutineScope: CoroutineScope,
            navController : NavHostController,
            gridState : LazyGridState,
            gameSelected : String? = "Monster Hunter World")
    {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Box(modifier = Modifier.fillMaxWidth().height(60.dp)){
                    Image(
                        painter = painterResource(R.drawable.pergamin_background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    TopAppBar(
                        title = { Text(text = selectedGame ?: "No game selected") },
                        navigationIcon = {
                            IconButton(onClick = {
                                toggleDrawer(drawerState = drawerState, coroutineScope = coroutineScope)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Open/Close Drawer"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black.copy(alpha = .0f),
                            titleContentColor = Color.White,
                            actionIconContentColor = Color.White
                        )
                    )
                }

            },
            bottomBar = {
                NavigationBar {
                    navigationCategories.forEach{ category ->
                        val selected = destination?.hierarchy?.any{it.hasRoute(category.route::class)} == true
                        NavigationBarItem(
                            selected = selected,
                            icon = Icon(category.selectedIcon, contentDescription = category.title),
                            label = Text(category.title),
                            onClick = {}
                        )
                    }
                }
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