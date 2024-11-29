package cat.institutmontivi.decissorviewmodel.ui.pantalles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.decissorviewmodel.R
import cat.institutmontivi.decissorviewmodel.ui.common.Boto

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun PantallaOraclePregunta (onClick:(String)->Unit = {})
{
    var pregunta by remember {mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.oracle),
                alpha = 0.6F,
                contentScale = ContentScale.FillBounds)){
        OutlinedTextField(
            value = pregunta ,
            onValueChange = {pregunta =  it},
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            label = { Text("Escriu la teva pregunta", style = MaterialTheme.typography.headlineSmall) },
            singleLine = false,
            maxLines = 5,
            textStyle = MaterialTheme.typography.headlineLarge
        )
        Boto (Modifier.align(Alignment.BottomCenter).padding(16.dp),text = "Respon", clic ={onClick(pregunta)} )
    }
}