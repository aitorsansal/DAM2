package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.monsterhunterapp.R
import com.aitorsansal.monsterhunterapp.data.dataRepository
import com.aitorsansal.monsterhunterapp.model.Monster

@Composable
fun VerticalFileInformation(id:String?,
                            monsterData : List<Monster>,
                            modifier : Modifier = Modifier,
                            background:Color = MaterialTheme.colorScheme.primary,
                            colorLetter:Color = MaterialTheme.colorScheme.onPrimaryContainer,
                            onClickElement : (String?) -> Unit = {},
){
    val monster = monsterData.firstOrNull {it.id == id }
    Box(modifier = modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.complete_mark),
            contentDescription = null,
            modifier = Modifier.fillMaxSize())


        Column(modifier = Modifier.fillMaxHeight()
            .clickable { onClickElement(id) }) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(monster?.image)
                    .size(250)
                    .build(), contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(25.dp).weight(2F),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_launcher_background)
            )
            Text(text = monster?.name ?: "no monster",
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).weight(.7F),
                textAlign = TextAlign.Center,
                color = colorLetter,
                overflow = TextOverflow.Ellipsis
                )
        }
    }

}


@Preview(heightDp = 250, widthDp = 150)
@Composable
fun PreviewVerticalFileInformation(){
    VerticalFileInformation("MHWorld-1",dataRepository.MHWorldData)
}
