package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.model.Monster
import com.aitorsansal.monsterhunterapp.R


@Composable
fun HorizontalFileInformation(
    monsterId: String?,
    modifier : Modifier = Modifier,
    background:Color = MaterialTheme.colorScheme.primary,
    colorLetter:Color = MaterialTheme.colorScheme.onPrimary,
    onClickElement : (String?) -> Unit = {}){
        val monster : Monster? = fakeRepository.MHRiseData.firstOrNull {it.id == monsterId} //todo change this shit
        Card {
            Row(modifier = modifier.fillMaxWidth().background(color = background)
                .clickable { onClickElement(monsterId) }) {
                Text(text = monster?.name ?: "no monster")
//                AsyncImage(
//                    model = ImageRequest
//                        .Builder(LocalContext.current)
//                        .data(monster.image)
//                        .size(250)
//                        .build(), contentDescription = null,
//                    modifier = Modifier.align(Alignment.CenterVertically),
//                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
//                    contentScale = ContentScale.Crop
//                )
                Spacer(modifier = Modifier.width(10.dp))
                    Spacer(modifier = Modifier.height(5.dp))
            }
        }
}

@Preview()
@Composable
fun HorizontalFileInformationPreview(){
    HorizontalFileInformation("MHWorld-1")
}