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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.navigation.NavigationGraph
import com.aitorsansal.monsterhunterapp.ui.theme.MonsterHunterAppTheme
import androidx.navigation.NavHostController
import com.aitorsansal.monsterhunterapp.data.GameData
import com.aitorsansal.monsterhunterapp.data.dataRepository.GetMonsterList
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunter4Ultimate
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunterRise
import com.aitorsansal.monsterhunterapp.navigation.MonsterHunterWorld
import com.aitorsansal.monsterhunterapp.navigation.NavigationCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val MonsterViewModelProvider = compositionLocalOf<MonsterViewModel>{error("No viewmodel passed")}

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
                dataRepository.obtainData(this)

                //viewModel
                val viewModel : MonsterViewModel = viewModel()
                // State for the Drawer
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                // Coroutine scope for drawer operations
                val coroutineScope = rememberCoroutineScope()

                //GameDetection
                var rememberMonsterData by remember {mutableStateOf(dataRepository.MHWorldData)}
                viewModel.setMonsters(rememberMonsterData)



                CompositionLocalProvider(MonsterViewModelProvider provides viewModel) {
                    NavigationDrawer(drawerState = drawerState, coroutineScope = coroutineScope,
                        navController = navController, actualRoute = currentRoute, actualDestination = destination)
                }
            }
        }
    }

}