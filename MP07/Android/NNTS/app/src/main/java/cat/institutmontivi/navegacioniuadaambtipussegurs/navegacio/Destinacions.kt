package cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.PantallaLlistaA



import kotlinx.serialization.Serializable


//Definició de les categories
@Serializable
object CategoriaA
@Serializable
object CategoriaB
@Serializable
object CategoriaC


data class CategoriaDeNavegacio<T:Any>(val ruta:T, val icona:ImageVector, val titol:String)
val categoriesDeNavegacio = listOf(
    CategoriaDeNavegacio(ruta = CategoriaA, icona = Icons.Default.Numbers, titol = "Números"),
    CategoriaDeNavegacio(ruta = CategoriaB, icona = Icons.Default.Numbers, titol = "Caràcters"),
    CategoriaDeNavegacio(ruta = CategoriaC, icona = Icons.Default.Numbers, titol = "Cadenes"),
)


//Definició de les pantalles de cada categoria
@Serializable
object LlistaA
@Serializable
data class  DetallA (val id:Int)
@Serializable
object LlistaB
@Serializable
data class DetallB (val id:Int)
@Serializable
object LlistaC
@Serializable
data class DetallC (val id:Int)

