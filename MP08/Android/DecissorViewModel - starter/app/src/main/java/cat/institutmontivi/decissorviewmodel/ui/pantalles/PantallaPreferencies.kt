package cat.institutmontivi.decissorviewmodel.ui.pantalles


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cat.institutmontivi.decissorviewmodel.dades.Preferences
import kotlinx.coroutines.flow.compose
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview
@Composable
fun PantallaPreferencies ()
{
    val prefernces = Preferences(LocalContext.current)
    val tempsCaraOCreu by prefernces.getTempsCaraOCreu.collectAsState(initial = 0)
    val tempsNumero by prefernces.getTempsNumero.collectAsState(initial = 0)
    val minimNumero by prefernces.getMinNum.collectAsState(initial = 0)
    val maximNumero by prefernces.getMaxNum.collectAsState(initial = 0)

    val coroutineScope = rememberCoroutineScope()
    Column (Modifier.fillMaxSize(). background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Preferències",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)
        Divider(modifier = Modifier.fillMaxWidth().height(3.dp))
        Column (Modifier.fillMaxSize(). background(color = MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){

            Spacer(modifier = Modifier.height(16.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )) {
                Text(text = "Cara o creu",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center)
                Divider(modifier = Modifier.fillMaxWidth().height(3.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Temps tirada en mil·lisegons",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center)
                Row (modifier = Modifier.fillMaxWidth()) {
                    Slider(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp).weight(1f),
                        valueRange = 0f..5000F,
                        value = tempsCaraOCreu.toFloat(),
                        onValueChange = {
                            coroutineScope.launch {
                                prefernces.setTempsCaraOCreu(it.toLong())
                            }
                        }
                    )
                    Text( modifier = Modifier.padding(start= 16.dp, end = 16.dp),
                        text = "$tempsCaraOCreu",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center)
                }
            }

        }
    }
}


