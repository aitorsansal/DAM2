package cat.institutmontivi.navegacioniuadaambtipussegurs.ui.comu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.navegacioniuadaambtipussegurs.R
import coil.compose.AsyncImage
import kotlin.coroutines.CoroutineContext

//@Preview(showSystemUi = true)
@Composable
fun ElementHoritzontal (dada: String = "A", id: Int = 0 , onClick: (Int) -> Unit = {})
{
    val dibuixos:List<Int> = listOf(
        R.drawable.drac,
        R.drawable.rosa,
        R.drawable.sant_jordi,
        R.drawable.windows
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(percent = 5))
            .border(shape = RoundedCornerShape(percent = 5), width = 1.dp, color =  MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(percent = 5)
            )
            .clickable (onClick = {onClick(id)})
        ,verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

    ) {
        AsyncImage(
            model = "https://loremflickr.com/150/150/$dada?lock=$id",
            contentDescription = null,
            placeholder = painterResource(dibuixos[id % dibuixos.size]),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .height(150.dp)
                .clip(shape = CircleShape)
                .weight(1f)
        )
        Text(
            text = dada,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(3f))

    }

}