package cat.institutmontivi.navegaciambtipussegurs.ui.pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.navegaciambtipussegurs.ui.comu.Casella



@Preview
@Composable
fun PantallaNumeros(it: PaddingValues = PaddingValues(0.dp),
                    onCasellaClick:(String) -> Unit = {}
){
    val dades = (1..100).toList()

    Column(
        modifier= Modifier
            .padding(it))
    {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Números:",
                modifier = Modifier.align(Alignment.CenterStart),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        )
        {
            items(dades)
            {
                Casella(text = it.toString(), onClick = {onCasellaClick(it.toString())}
                )
            }
        }
    }
}