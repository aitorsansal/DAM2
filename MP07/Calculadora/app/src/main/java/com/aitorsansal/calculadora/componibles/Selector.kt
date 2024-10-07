package com.aitorsansal.calculadora.componibles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aitorsansal.calculadora.dades.DadesFake

@Composable
fun Selector(
    dades: List<String>,
    opcioSeleccionada: Int = 0,
    modifier: Modifier = Modifier,
    onCanviSeleccio : (Int) -> Unit = {},
    gruixMarc: Dp = 2.dp,
    colorMarc: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    colorFons: Color = MaterialTheme.colorScheme.primaryContainer,
    colorText: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    estilText: TextStyle = MaterialTheme.typography.displaySmall,
    colorTextSeleccionat: Color = MaterialTheme.colorScheme.onBackground,
    colorFonsSelecionat: Color = MaterialTheme.colorScheme.inverseOnSurface,
    )
{
    var selected by remember { mutableStateOf(opcioSeleccionada) }
    Row()
    {
        dades.forEachIndexed{
            index, s ->
            Text(text = dades[index],
                style = estilText ,
                color = if (selected==index)
                {
                    colorTextSeleccionat
                }
                else
                    colorText,
                modifier = Modifier
                    .background(
                        if (selected==index)
                            colorFonsSelecionat
                        else
                            colorFons
                    )
                    //PER FER CLICABLE NO BOTONS
                    .clickable {
                        //CANVIEM TANT VARIABLE LOCAL COM ESTAT PARAMETRE
                        selected=index
                        onCanviSeleccio(selected)
                    }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSelector()
{
    Selector(DadesFake.diesDeLaSetmana)
}