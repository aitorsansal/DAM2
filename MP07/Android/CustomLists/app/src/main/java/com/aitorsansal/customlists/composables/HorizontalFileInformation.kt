package com.aitorsansal.customlists.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.customlists.R
import com.aitorsansal.customlists.data.fakeRepository
import com.aitorsansal.customlists.model.Monster


@Composable
fun HorizontalFileInformation(
    id:Int,
    modifier : Modifier = Modifier,
    background:Color = MaterialTheme.colorScheme.background,
    colorLetter:Color = MaterialTheme.colorScheme.onBackground,
            onClickElement : (Int) -> Unit = {}){
    val monster : Monster = fakeRepository.obtainData()[id]
    Row(modifier = modifier.fillMaxWidth().background(color = background)
        .clickable { onClickElement(id) }) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(monster.image)
                .size(250)
                .build(), contentDescription = null,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column (modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = monster.name, color = colorLetter)
            Spacer(modifier = Modifier.height(16.dp))
            if(monster.completedCatchingChallenge)
                Text(text = "Mission complete!\n " +
                        "${monster.quantityCaptured}/${monster.totalToCapture}",
                    color = Color.Green)
            else
                Text(text = "Mission in progress\n " +
                        "${monster.quantityCaptured}/${monster.totalToCapture}",
                    color = Color.Red)
        }
    }

}

@Preview
@Composable
fun HorizontalFileInformationPreview(){
    HorizontalFileInformation(1)
}