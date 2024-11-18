package com.aitorsansal.scrollabledetail

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.aitorsansal.scrollabledetail.navigation.MHWorldListNav
import com.aitorsansal.scrollabledetail.navigation.NavigationGraph
import com.aitorsansal.scrollabledetail.navigation.navigationCategories
import kotlinx.coroutines.CoroutineScope

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationApp(drawerState: DrawerState, coroutineScope: CoroutineScope,
        navController : NavHostController,
        destination : NavDestination?,
        selectedGame : String? = "Monster Hunter World")
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().height(60.dp)){
                TopAppBar(
                    title = { Text(text = selectedGame ?: "No game selected") },
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

        },
        bottomBar = {
            NavigationBar {
                navigationCategories.forEach{ category ->
                    val selected = destination?.hierarchy?.any{it.hasRoute(category.route::class)} == true
                    NavigationBarItem(
                        selected = selected,
                        icon = {Icon(painter = painterResource(category.icon), contentDescription = category.title)},
                        label = {Text(category.title)},
                        onClick = {
                            navController.navigate(category.route){
                                popUpTo(MHWorldListNav)
                            }
                        },
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        NavigationGraph(navController, paddingValues = paddingValues)
    }
}