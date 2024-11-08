package cat.institutmontivi.navegacioniuadaambtipussegurs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.CategoriaA
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.GrafDeNavegacio
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.LlistaA
import cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio.categoriesDeNavegacio

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun AppBarraDeNavegacio() {
    val controladorDeNavegacio = rememberNavController();
    val rutaActual by controladorDeNavegacio.currentBackStackEntryAsState()
    val destinacioActual = rutaActual?.destination

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
                            onClick = { }
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
        },
        bottomBar = {
            NavigationBar {
                categoriesDeNavegacio.forEach {categoria->
                    NavigationBarItem(
                        selected = destinacioActual?.hierarchy?.any {it.hasRoute(categoria.ruta::class) } == true,
                        onClick = {
                            if(destinacioActual?.hasRoute(CategoriaA::class) == false) {
                                controladorDeNavegacio.popBackStack()
                            }
                            controladorDeNavegacio.navigate(categoria.ruta){
                                //No sé perquè, això no funciona amb navegació amb tipus segurs
                                popUpTo(controladorDeNavegacio.graph.findStartDestination().id){
                                    inclusive = false
                                    //Desa l'estat de la ruta de navegació on som
                                    saveState = true
                                }
                                //Si piquem diverses vegades sobre la barra de navegació, només es tornarà a carregar la pantalla si no és la mateixa
                                launchSingleTop = true
                                //Restaura l'estat de la pantalla de la que venim
                                restoreState = true
                            }
                        },
                        icon = {Icon(imageVector = categoria.icona, contentDescription = categoria.titol)},
                        label = { Text(text = categoria.titol) }
                    )
                }
            }
        }
    ) { innerPadding ->
        GrafDeNavegacio(
            controladorDeNavegacio = controladorDeNavegacio,
            paddingValues = innerPadding)

    }
}