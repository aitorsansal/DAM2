package cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallC
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaC
import cat.institutmontivi.navegacioniuadaambtipussegurs.dades.FakeDataSource
import coil.decode.DataSource

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues : PaddingValues = PaddingValues(0.dp)){

    NavHost(
        navController = navController,
        startDestination = CategoriaA,
        modifier = Modifier.padding(paddingValues = paddingValues)
    )
    {
        CategoriaA(navController = navController)
        CategoriaB(navController = navController)
        CategoriaC(navController = navController)
    }

}

fun NavGraphBuilder.CategoriaA(navController : NavHostController) {
    navigation<CategoriaA>(startDestination = LlistaA) {
        composable<LlistaA> {
            PantallaLlistaA(llista = FakeDataSource.llistaA,
                onClickElement = {
                    navController.navigate(DetallA(it))
                })
        }
        composable<DetallA> {
            val argument = it.toRoute<DetallA>()
            PantallaDetallA(argument.numero)
        }
    }
}
fun NavGraphBuilder.CategoriaB(navController : NavHostController) {
    navigation<CategoriaB>(startDestination = LlistaB) {
        composable<LlistaB> {
            PantallaLlistaB(llista = FakeDataSource.llistaB,
                onClickElement = {
                    navController.navigate(DetallB(it))
                })
        }
        composable<DetallB> {
            val argument = it.toRoute<DetallB>()
            PantallaDetallB(argument.caracter)
        }
    }
}
fun NavGraphBuilder.CategoriaC(navController : NavHostController) {
    navigation<CategoriaC>(startDestination = LlistaC) {
        composable<LlistaC> {
            PantallaLlistaC(llista = FakeDataSource.llistaC,
                onClickElement = {
                    navController.navigate(DetallC(it))
                })
        }
        composable<DetallC> {
            val argument = it.toRoute<DetallC>()
            PantallaDetallC(argument.cadena)
        }
    }
}