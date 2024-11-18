package com.aitorsansal.monsterhunterapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.ui.theme.MonsterHunterAppTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterHunterAppTheme {
                dataRepository.obtainData(this)
                MHNormalApp()
//                AppPager()
            }
        }
    }

}

@Composable
fun MHNormalApp(){
    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()
    val destination = currentRoute?.destination

    //viewModel
    // State for the Drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Coroutine scope for drawer operations
    val coroutineScope = rememberCoroutineScope()



    NavigationDrawer(drawerState = drawerState, coroutineScope = coroutineScope,
        navController = navController, actualRoute = currentRoute, actualDestination = destination)
}