package com.aitorsansal.modalbottomsheet.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.modalbottomsheet.data.dataRepository
import com.aitorsansal.modalbottomsheet.model.Monster
import com.aitorsansal.modalbottomsheet.R


@Composable
fun HorizontalFileInformation(
    monsterId: String,
    modifier : Modifier = Modifier,
    colorLetter:Color = MaterialTheme.colorScheme.onPrimary,
    onClickElement : (String?) -> Unit = {}){

    val monsters : List<Monster> = when (monsterId.split("-")[0]){
        "MHWorld" -> dataRepository.MHWorldData
        "MHRise" -> dataRepository.MHRiseData
        "MH4" -> dataRepository.MH4UData
        "MHWilds" -> dataRepository.MHWildsData
        else -> {
            listOf()
        }
    }
    val monster = monsters.firstOrNull { it.id == monsterId}

    Box(modifier = modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.complete_mark_horizontal), null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Row(modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(monster?.image)
                    .size(250)
                    .build(), contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 10.dp),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop
            )
            Text(text = monster?.name ?: "no monster", modifier = Modifier.align(Alignment.CenterVertically).weight(1F))
            IconButton(onClick = {onClickElement(monsterId)}, modifier = Modifier.padding(horizontal = 25.dp)){
                Icon(imageVector = Icons.Default.MoreVert, null)
            }
        }
    }


}

@Preview()
@Composable
fun HorizontalFileInformationPreview(){
    dataRepository.obtainData(LocalContext.current)
    HorizontalFileInformation("MHWorld-1",
        modifier = Modifier.width(500.dp).height(100.dp))
}