package cat.institutmontivi.decissorviewmodel.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.institutmontivi.decissorviewmodel.R
import cat.institutmontivi.decissorviewmodel.dades.Preferences
import cat.institutmontivi.decissorviewmodel.ui.common.Boto
import cat.institutmontivi.decissorviewmodel.ui.viewmodels.ViewModelCaraOCreu
import androidx.compose.runtime.getValue

@Preview
@Composable
fun PantallaCaraOCreu (viewModel: ViewModelCaraOCreu = viewModel())
{
    val estat = viewModel.estat
    val time by Preferences(LocalContext.current).getTempsCaraOCreu.collectAsState(initial = 0)
    Column(
        Modifier
            .padding(32.dp)
            .fillMaxSize())
    {
        var image = R.drawable.question
        if(estat.resultat == 0){
            image = R.drawable.question
        }
        else if(estat.resultat == 1)
            image = R.drawable.carapng
        else if(estat.resultat == 2)
            image = R.drawable.creupng

        Image(painter = painterResource(id = image), contentDescription = null,
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Fit)
        if(!estat.estaSortejant)
            Boto(
                modifier = Modifier
                    .weight(0.75F)
                    .fillMaxHeight(),
                text = "Sorteja",
                clic = { viewModel.sorteja(time) })
        else
            CircularProgressIndicator()
    }
}
