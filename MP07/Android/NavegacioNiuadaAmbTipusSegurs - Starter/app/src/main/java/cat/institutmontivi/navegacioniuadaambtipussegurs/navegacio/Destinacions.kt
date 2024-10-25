package cat.institutmontivi.navegacioniuadaambtipussegurs.navegacio

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Nature
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

//Define categories
@Serializable
object CategoriaA
@Serializable
object CategoriaB
@Serializable
object CategoriaC

data class CategoriaDeNavegacio<T:Any> (
    val ruta:T,
    val iconaNoSeleccionada: ImageVector,
    val iconaSeleccionada: ImageVector,
    val titol:String
)


val categoriesDeNavegacio = listOf(
    CategoriaDeNavegacio<CategoriaA>(
        ruta = CategoriaA,
        iconaSeleccionada = Icons.Filled.Nature,
        iconaNoSeleccionada = Icons.Outlined.Nature,
        titol = "Categoria A"
    ),
    CategoriaDeNavegacio<CategoriaB>(
        ruta = CategoriaB,
        iconaSeleccionada = Icons.Filled.AreaChart,
        iconaNoSeleccionada = Icons.Outlined.AreaChart,
        titol = "Categoria B"
    ),
    CategoriaDeNavegacio<CategoriaC>(
        ruta = CategoriaC,
        iconaSeleccionada = Icons.Filled.Call,
        iconaNoSeleccionada = Icons.Outlined.Call,
        titol = "Categoria C"
    )
)


@Serializable
object LlistaA
@Serializable
data class DetallA(val numero:Int)
@Serializable
object LlistaB
@Serializable
data class DetallB(val caracter:String)
@Serializable
object LlistaC
@Serializable
data class DetallC(val cadena: String)