package com.aitorsansal.calculadora.componibles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Comptador(
    modifier: Modifier = Modifier,
    gruixMarc: Dp = 2.dp,
    colorMarc: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    colorFons: Color = MaterialTheme.colorScheme.primaryContainer,
    colorText: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    estilText: TextStyle = MaterialTheme.typography.displayMedium,
    onCanviQuant: (Int) -> Unit = {},
)
{
    Row(modifier = modifier //El del parametre.
        .border(
            width=gruixMarc,
            color=colorMarc,
            shape = RoundedCornerShape(10.dp),
            )
        .clip(RoundedCornerShape(10.dp))
        .background(color=colorFons)
        .padding(8.dp)
        )
    {
        var quant by remember {mutableStateOf(0)}

        Text("$quant", style = estilText, color = colorText, modifier = Modifier.align(Alignment.CenterVertically))

        Column {
            IconButton(
                onClick = {
                    quant++
                    //Canviem també l'estat
                    onCanviQuant(quant)
            })
            {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
            IconButton(
                onClick = {
                    quant--
                    //Canviem també l'estat
                    onCanviQuant(quant)
            }, enabled = quant>0,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )

            {
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewComptador()
{
    Comptador()
}