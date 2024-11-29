package cat.institutmontivi.decissorviewmodel.navegacio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaCaraOCreu
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaInstruccions
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaOraclePregunta
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaOracleResposta
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaPortada
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaPreferencies
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaQuantA
import cat.institutmontivi.decissorviewmodel.ui.pantalles.PantallaTriaUnNumero


@Composable
fun GrafDeNavegacio (controladorDeNavegacio: NavHostController = rememberNavController(), paddingValues: PaddingValues = PaddingValues(0.dp))
{
    NavHost(navController = controladorDeNavegacio, startDestination =DestinacioPortada, modifier = Modifier.padding(paddingValues))
    {
        composable<DestinacioPortada> {
            PantallaPortada()
        }

        composable<DestinacioInstruccions> {
            PantallaInstruccions()
        }

        composable<DestinacioPreferencies> {
            PantallaPreferencies()
        }

        composable<DestinacioQuantA> {
            PantallaQuantA()
        }

        composable<DestinacioCaraOCreu> {
            PantallaCaraOCreu()
        }

        composable<DestinacioTriaNumero> {
            PantallaTriaUnNumero()
        }

        composable<DestinacioOraclePregunta> {
            PantallaOraclePregunta(
                onClick = {pregunta:String -> controladorDeNavegacio.navigate(DestinacioOracleResposta(pregunta)) }
            )
        }

        composable<DestinacioOracleResposta> {
            val argument = it.toRoute<DestinacioOracleResposta>()
            PantallaOracleResposta(
                pregunta = argument.pregunta,
              )
        }
    }

}

