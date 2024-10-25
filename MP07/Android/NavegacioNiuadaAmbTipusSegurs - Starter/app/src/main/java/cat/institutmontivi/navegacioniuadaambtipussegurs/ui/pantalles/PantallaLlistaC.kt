package cat.institutmontivi.navegaciobarrainferior.ui.pantalles


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.navegacioniuadaambtipussegurs.dades.FakeDataSource
import cat.institutmontivi.navegacioniuadaambtipussegurs.ui.comu.ElementHoritzontal

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun PantallaLlistaC (llista: List<String> = FakeDataSource.llistaC, onClickElement: (String) -> Unit = {}){
    LazyColumn(){
        stickyHeader { Text (
            text = "Llista C",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onTertiary,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center)
        }
        itemsIndexed(llista)
        {index, element ->
            ElementHoritzontal(dada = element.toString(),index, onClick = {onClickElement(element.toString())})
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}