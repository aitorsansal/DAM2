package com.aitorsansal.modalbottomsheet

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.aitorsansal.modalbottomsheet.data.dataRepository
import com.aitorsansal.modalbottomsheet.navigation.MHWorldListNav
import com.aitorsansal.modalbottomsheet.navigation.NavigationGraph
import com.aitorsansal.modalbottomsheet.navigation.navigationCategories
import com.aitorsansal.modalbottomsheet.ui.screens.HorizontalComposableList
import com.aitorsansal.modalbottomsheet.ui.theme.ModalBottomSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModalBottomSheetTheme {
                dataRepository.obtainData(LocalContext.current)
                val navController = rememberNavController()
                val currentRoute by navController.currentBackStackEntryAsState()
                val destination = currentRoute?.destination
                App(navController, destination)
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun App(navController : NavHostController,
        destination : NavDestination?){
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isCompacted = windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT
    val activity = LocalContext.current as Activity
    var elementWidth = 500.dp
    if(isCompacted)
        elementWidth = 1000.dp
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            if(!isCompacted)
                IconButton(onClick = {activity.finish()}) {
                    Icon(imageVector = Icons.Default.Home, null)
                }
        },
        bottomBar = {
            if(!isCompacted)
                BottomBar(destination, navController)
        }
    ) { paddingValues ->
        Row(modifier = Modifier.padding(paddingValues)) {
            if(isCompacted){
                NavigationRail (
                    header = {
                        IconButton(onClick = {activity.finish()}) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp , null)
                        }
                    }
                ){
                    navigationCategories.forEach { category ->
                        val selected =
                            destination?.hierarchy?.any { it.hasRoute(category.route::class) } == true
                        NavigationRailItem(
                            selected = selected,
                            icon = {
                                Icon(
                                    painter = painterResource(category.icon),
                                    contentDescription = category.title
                                )
                            },
//                            label = { Text(category.title) },
                            onClick = {
                                navController.navigate(category.route) {
                                    popUpTo(MHWorldListNav)
                                }
                            },
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            NavigationGraph(navController, elementWidth = elementWidth)
        }
    }
}


@SuppressLint("RestrictedApi")
@Composable
private fun BottomBar(
    destination: NavDestination?,
    navController: NavHostController
) {
    NavigationBar {
        navigationCategories.forEach { category ->
            val selected =
                destination?.hierarchy?.any { it.hasRoute(category.route::class) } == true
            NavigationBarItem(
                selected = selected,
                icon = {
                    Icon(
                        painter = painterResource(category.icon),
                        contentDescription = category.title
                    )
                },
                label = { Text(category.title) },
                onClick = {
                    navController.navigate(category.route) {
                        popUpTo(MHWorldListNav)
                    }
                },
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)) {
        TopAppBar(
            title = { Text(text = "No game selected") },
            navigationIcon = {
                IconButton(onClick = {}) {
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
}