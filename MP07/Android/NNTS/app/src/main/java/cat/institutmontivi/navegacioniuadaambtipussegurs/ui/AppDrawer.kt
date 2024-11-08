package cat.institutmontivi.navegacioniuadaambtipussegurs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.GrafDeNavegacio
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.LlistaA
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.categoriesDeNavegacio
import kotlinx.coroutines.CoroutineScope
import cat.institutmontivi.navegacioniuadaambtipussegurs.R
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.LlistaB
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.LlistaC
import kotlinx.coroutines.launch

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
    //Bastida(rutaActual, destinacioActual, controladorDeNavegacio)
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
            drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
            drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            drawerTonalElevation = 5.dp,
            drawerShape = ShapeDefaults.Medium,
            windowInsets = WindowInsets(left = 24.dp, top = 48.dp, right = 24.dp, bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.drac),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(48.dp))
            categoriesDeNavegacio.forEach {cat ->
                NavigationDrawerItem(
                    label = {Text(cat.titol)},
                    selected = actualDestination?.hierarchy?.any{
                        it.hasRoute(cat.ruta::class)}==true,
                    icon = {Icon(imageVector = cat.icona, null)},
                    onClick = {
                        coroutineScope.launch{
                            drawerState.close()
                        }
                        navController.navigate(cat.ruta){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState=true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedBadgeColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedBadgeColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.height(15.dp)
                )
            }
        }
    },
        gesturesEnabled = listOf(
            LlistaA::class,
            LlistaB::class,
            LlistaC::class
        ).any{actualDestination?.hasRoute(it) == true}
    ) {
        Bastida(actualRoute, actualDestination, navController, drawerState, coroutineScope)
    }
}

@SuppressLint("RestrictedApi")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Bastida(
    rutaActual: NavBackStackEntry?,
    destinacioActual: NavDestination?,
    controladorDeNavegacio: NavHostController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Navegació amb tipus segurs - ${
                            rutaActual?.destination?.route?.substringAfterLast(
                                "."
                            ) ?: "Principal"
                        }"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    if (destinacioActual?.hasRoute(LlistaA::class) ?: true) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch{
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Pantalla principal",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { controladorDeNavegacio.navigateUp() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navega enrera",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        GrafDeNavegacio(
            controladorDeNavegacio = controladorDeNavegacio,
            paddingValues = innerPadding
        )

    }
}

