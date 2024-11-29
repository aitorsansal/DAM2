package cat.institutmontivi.decissorviewmodel.ui.pantalles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.institutmontivi.decissorviewmodel.ui.common.Boto
import cat.institutmontivi.decissorviewmodel.ui.viewmodels.ViewModelTriaNumero

@Preview
@Composable
fun PantallaTriaUnNumero (viewModel : ViewModelTriaNumero = viewModel())
{
    val min = 1
    val max = 1000
    val time : Long = 1500
    Column(
        Modifier
            .padding(32.dp)
            .fillMaxSize())
    {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)) {
            Text(
                if(viewModel.estat.estaSortejant)
                    "???"
                else
                    "${viewModel.estat.valorTriat}",
                Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.displayLarge,
                fontSize = 148.sp,
                textAlign = TextAlign.Center
            )
        }

        if(!viewModel.estat.estaSortejant)
            Boto(
                modifier = Modifier
                    .weight(0.75F)
                    .fillMaxHeight(),
                text = "Sorteja",
                clic = { viewModel.Sorteja(min, max, time)})
        else
            CircularProgressIndicator()
    }
}