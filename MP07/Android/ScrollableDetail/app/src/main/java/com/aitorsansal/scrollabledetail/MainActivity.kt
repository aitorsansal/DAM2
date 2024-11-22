package com.aitorsansal.scrollabledetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.scrollabledetail.data.dataRepository
import com.aitorsansal.scrollabledetail.ui.theme.ScrollableDetailTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollableDetailTheme {
                dataRepository.obtainData(this)
                MHNormalApp()
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

    BottomNavigationApp(navController = navController, destination = destination)
}