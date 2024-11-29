package cat.institutmontivi.decissorviewmodel.ui.pantalles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.decissorviewmodel.R

@Preview
@Composable
fun PantallaOracleResposta (pregunta: String = "Aquí va la pregunta")
{
    Column(
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.oracle_resposta),
                contentScale = ContentScale.Fit,
                alpha = 0.6F
            )){
        Spacer(modifier = Modifier.weight(1f))

        Text(text = pregunta, Modifier.align(Alignment.CenterHorizontally).padding(16.dp), style = MaterialTheme.typography.displaySmall)

        Spacer(modifier = Modifier.weight(3f))

        Text(text = "", Modifier.align(Alignment.CenterHorizontally).padding(16.dp), style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.weight(1f))
    }
}