package com.aitorsansal.monsterhunterapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aitorsansal.monsterhunterapp.navigation.MH4UList
import com.aitorsansal.monsterhunterapp.navigation.MHRiseList
import com.aitorsansal.monsterhunterapp.navigation.MHWorldList
import kotlinx.coroutines.CoroutineScope
import com.aitorsansal.monsterhunterapp.navigation.NavigationGraph
import com.aitorsansal.monsterhunterapp.navigation.navigationCategories
import com.aitorsansal.monsterhunterapp.ui.composables.CustomDrawerItem
import kotlinx.coroutines.launch
import kotlin.collections.any
import kotlin.collections.forEach
import kotlin.sequences.any
import kotlin.text.substringAfterLast

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppDrawer() {
    val navController = rememberNavController();
    val actualRoute by navController.currentBackStackEntryAsState()
    val actualDestination = actualRoute?.destination


    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()



    NavigationDrawer(navController, actualRoute, actualDestination, drawerState, coroutineScope)
}

@SuppressLint("RestrictedApi")
@Composable
fun NavigationDrawer(
    navController: NavHostController,
    actualRoute: NavBackStackEntry?,
    actualDestination: NavDestination?,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Black.copy(alpha = .7f),
                drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                drawerTonalElevation = 5.dp,
                drawerShape = ShapeDefaults.Medium,
                windowInsets = WindowInsets(
                    left = 24.dp,
                    top = 48.dp,
                    right = 24.dp,
                    bottom = 24.dp
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.mh_logo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(24.dp))
                navigationCategories.forEach { cat ->
                    CustomDrawerItem(
                        modifier = Modifier.height(100.dp),
                        label = cat.title,
                        selected = actualDestination?.hierarchy?.any {
                            it.hasRoute(cat.route::class)
                        } == true,
                        icon = painterResource(cat.icon),
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navController.navigate(cat.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        gesturesEnabled = listOf(
            MHWorldList::class,
            MHRiseList::class,
            MH4UList::class
        ).any { actualDestination?.hasRoute(it) == true }
    ) {
        App(drawerState = drawerState, coroutineScope = coroutineScope, navController = navController, actualRoute = actualRoute)
    }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(drawerState: DrawerState, coroutineScope: CoroutineScope,
        navController : NavHostController,
        actualRoute : NavBackStackEntry?)
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
                    title = { Text(actualRoute?.destination?.route?.substringAfterLast(".") ?: "Monster Hunter World") },
                    navigationIcon = {
                        if(listOf(MHWorldList::class,
                                MHRiseList::class,
                                MH4UList::class
                            ).any { actualRoute?.destination?.hasRoute(it) == true }) {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Open/Close Drawer"
                                )
                            }
                        }
                        else{
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Open/Close Drawer"
                                )
                            }
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
    ) { paddingValues ->
        NavigationGraph(navController, paddingValues = paddingValues)
    }
}

