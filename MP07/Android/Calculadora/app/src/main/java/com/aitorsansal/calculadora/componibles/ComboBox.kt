package com.aitorsansal.calculadora.componibles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aitorsansal.calculadora.dades.DadesFake

@Composable
fun ComboBox(
    dades: List<String>,
    opcioSeleccionada: Int = 0,
    modifier: Modifier = Modifier,
    onCanviSeleccio : (Int) -> Unit = {},
    gruixMarc: Dp = 2.dp,
    colorMarc: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    colorFons: Color = MaterialTheme.colorScheme.primaryContainer,
    colorText: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    estilText: TextStyle = MaterialTheme.typography.displaySmall,
    colorTextSeleccionat: Color = MaterialTheme.colorScheme.primaryContainer,
    colorFonsSelecionat: Color = MaterialTheme.colorScheme.onPrimaryContainer
){
    var selected by remember { mutableStateOf(opcioSeleccionada) }
    var isOpened by remember {mutableStateOf(false)}
    Column(modifier = modifier
        .padding(2.dp)
        .border(width = gruixMarc,
            color = colorMarc,
            shape = RoundedCornerShape(2.dp))
        .clip(shape = RoundedCornerShape(2.dp))
        .background(color = colorFons)
        .padding(4.dp)){
        Row(){
            Text(text = dades[selected], style = estilText, color = colorTextSeleccionat,
                modifier = Modifier.background(color = colorFonsSelecionat).padding(3.dp) )
            Spacer(modifier = Modifier.width(5.dp))
            IconButton({isOpened = !isOpened}, modifier = Modifier
                .clip(shape = RoundedCornerShape(90.dp))
                .background(color = colorFonsSelecionat)
                .size(35.dp)
                .align(alignment = Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        }
        if(isOpened)
        {
            Column()
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
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                            .background(
                                if (selected==index)
                                    colorFonsSelecionat
                                else
                                    colorFons
                            )
                            .clickable {
                                selected=index
                                onCanviSeleccio(selected)
                                isOpened = false;
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComboBoxPreview(){
    ComboBox(DadesFake.diesDeLaSetmana)
}