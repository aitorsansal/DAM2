package cat.institutmontivi.navegaciambtipussegurs.ui.pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PantallaElement(
    text: String = "74",
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge,
    backColor: Color = MaterialTheme.colorScheme.errorContainer,
    borderColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    textColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    fontSize: TextUnit = 256.sp
)
{
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .border(width = 8.dp, color = borderColor)
            .background(backColor)
    ) {
        Text(
            text,
            modifier = Modifier.align(Alignment.Center),
            color = textColor,
            style = textStyle,
            fontSize = fontSize
        )
    }
}