package cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaDetallC
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaC
import cat.institutmontivi.navegacioniuadaambtipussegurs.dades.obtenLlistaA
import cat.institutmontivi.navegacioniuadaambtipussegurs.dades.obtenLlistaB
import cat.institutmontivi.navegacioniuadaambtipussegurs.dades.obtenLlistaC


@Composable
fun GrafDeNavegacio (controladorDeNavegacio: NavHostController = rememberNavController(), paddingValues: PaddingValues = PaddingValues(0.dp))
{
    NavHost(navController = controladorDeNavegacio, startDestination = CategoriaA, modifier = Modifier.padding(paddingValues))
    {
        CategoriaA(controladorDeNavegacio)
        CategoriaB(controladorDeNavegacio)
        CategoriaC(controladorDeNavegacio)
    }
}



//Definició dels grafs de navegació de cada categoria
//Es fa creant funcions d'extensió de la classe NavGraphBuilder

fun NavGraphBuilder.CategoriaA(controladorDeNavegacio: NavHostController) {
    navigation<CategoriaA>(startDestination = LlistaA){

        composable<LlistaA>{
            PantallaLlistaA(
                llista = obtenLlistaA(),
                onClickElement = {id:Int -> controladorDeNavegacio.navigate(DetallA(id))}
            )
        }

        composable<DetallA>{
            val argument = it.toRoute<DetallA>()
            PantallaDetallA(argument.id)
        }
    }
}

fun NavGraphBuilder.CategoriaB(controladorDeNavegacio: NavHostController) {
    navigation<CategoriaB>(startDestination = LlistaB){
        composable<LlistaB>{
            PantallaLlistaB(
                llista = obtenLlistaB(),
                onClickElement = {id:Int-> controladorDeNavegacio.navigate(DetallB(id))}
            )
        }

        composable<DetallB>{
            val argument = it.toRoute<DetallB>()
            PantallaDetallB(argument.id)
        }
    }
}

fun NavGraphBuilder.CategoriaC(controladorDeNavegacio: NavHostController) {
    navigation<CategoriaC>(startDestination = LlistaC){
        composable<LlistaC>{
            PantallaLlistaC(
                llista = obtenLlistaC(),
                onClickElement = {id:Int->controladorDeNavegacio.navigate(DetallC(id))}
            )
        }

        composable<DetallC>{
            val argument = it.toRoute<DetallC>()
            PantallaDetallC(argument.id)
        }
    }
}

