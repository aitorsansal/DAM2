package cat.institutmontivi.decissorviewmodel.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object DestinacioPortada

@Serializable
object DestinacioCaraOCreu

@Serializable
object DestinacioTriaNumero

@Serializable
object DestinacioOraclePregunta

@Serializable
data class DestinacioOracleResposta(val pregunta: String)

@Serializable
object DestinacioPreferencies

@Serializable
object DestinacioInstruccions

@Serializable
object DestinacioQuantA

data class OpcioDrawer<T:Any>(val ruta:T, val iconaNoSeleccionada: ImageVector, val iconaSeleccionada: ImageVector, val titol:String, val mostraInsignia:Boolean = false, val IconaInsignia:ImageVector = Icons.Default.Star)

val opcionsDrawer = listOf(
    OpcioDrawer(DestinacioPortada, Icons.Outlined.Home, Icons.Filled.Home, "Portada"),
    OpcioDrawer(DestinacioCaraOCreu, Icons.Outlined.AddCircle, Icons.Filled.Star, "Cara o Creu"),
    OpcioDrawer(DestinacioTriaNumero, Icons.Outlined.Numbers, Icons.Filled.Star, "Tria un número"),
    OpcioDrawer(DestinacioOraclePregunta, Icons.Outlined.QuestionMark, Icons.Filled.Star, "Pregunta a l'Oracle"),
    OpcioDrawer(DestinacioPreferencies, Icons.Outlined.Settings, Icons.Filled.Star, "Preferències", true, Icons.Filled.Star),
    OpcioDrawer(DestinacioInstruccions, Icons.Outlined.Info, Icons.Filled.Star, "Instruccions"),
    OpcioDrawer(DestinacioQuantA, Icons.Outlined.Chat, Icons.Filled.Star, "Quant a ..."),
)