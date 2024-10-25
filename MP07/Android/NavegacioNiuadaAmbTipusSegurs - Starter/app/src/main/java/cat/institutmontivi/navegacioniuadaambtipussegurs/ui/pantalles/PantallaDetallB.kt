package cat.institutmontivi.navegaciobarrainferior.ui.pantalles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PantallaDetallB (lletra: String = "a"){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ){
        Text(text = lletra.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(25.dp))
                .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondaryContainer), shape= RoundedCornerShape(25.dp))
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                .padding(30.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            style = MaterialTheme.typography.displayLarge
        )
    }
}