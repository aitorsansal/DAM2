package com.aitorsansal.rps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.rps.ui.theme.RPSTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RPSTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val actualRoute by navController.currentBackStackEntryAsState()
    val actualDestination = actualRoute?.destination

    CustomNavigationDrawer(navController = navController, coroutineScope = coroutineScope, drawerState = drawerState, actualRoute = actualRoute, actualDestination = actualDestination)
}