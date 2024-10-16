package com.aitorsansal.customlists.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.customlists.data.fakeRepository
import com.aitorsansal.customlists.model.Monster
import com.aitorsansal.testing.R

@Composable
fun VerticalFileInformation(id:Int,
                            modifier : Modifier = Modifier,
                            background:Color = MaterialTheme.colorScheme.primary,
                            colorLetter:Color = MaterialTheme.colorScheme.onPrimary,
                            onClickElement : (Int) -> Unit = {}
){
    if(id < fakeRepository.monsterData.size) {
        val monster: Monster = fakeRepository.monsterData[id] //todo change this shit
        Card {
            Column(modifier = modifier.fillMaxHeight().background(color = background)
                .clickable { onClickElement(id) }) {
                Row(modifier = Modifier.fillMaxWidth().weight(.6f),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Completed challenge:", color = colorLetter,
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.CenterVertically))
                    CustomCheck(checked = monster.completedCatchingChallenge,
                        color = colorLetter,
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(monster.image)
                        .size(250)
                        .build(), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally).weight(2F),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop
                )
                Text(text = monster.name,
                    modifier = Modifier.fillMaxSize().weight(.6F),
                    textAlign = TextAlign.Center,
                    color = colorLetter)
            }
        }
    }
}


@Preview(heightDp = 250, widthDp = 150)
@Composable
fun PreviewVerticalFileInformation(){
    VerticalFileInformation(108)
}