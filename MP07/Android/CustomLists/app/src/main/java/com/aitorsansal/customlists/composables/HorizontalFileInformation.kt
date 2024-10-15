package com.aitorsansal.customlists.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
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
    background:Color = MaterialTheme.colorScheme.primary,
    colorLetter:Color = MaterialTheme.colorScheme.onPrimary,
            onClickElement : (Int) -> Unit = {}){
    if(id < fakeRepository.obtainData().size)
    {
        val monster : Monster = fakeRepository.obtainData()[id] //todo change this shit
        Card {
            Row(modifier = modifier.fillMaxWidth().background(color = background)
                .clickable { onClickElement(id) }) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(monster.image)
                        .size(250)
                        .build(), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(text = monster.name, color = colorLetter,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                    if(monster.completedCatchingChallenge) {
                        Text(text = "Mission complete!", color = Color.Green)
                        Spacer(modifier = Modifier.height(5.dp))
                        ProgressBar(monster.quantityCaptured, monster.totalToCapture,
                            modifier = Modifier.height(40.dp),
                            showNumbers = true)
                    }
                    else if(monster.quantityCaptured.toFloat()/monster.totalToCapture.toFloat() >=0.75)
                    {
                        Text(text = "Mission in progress", color = Color.Yellow)
                        Spacer(modifier = Modifier.height(5.dp))
                        ProgressBar(monster.quantityCaptured, monster.totalToCapture,
                            modifier = Modifier.height(50.dp),
                            showNumbers = true,
                            frontColor = Color(215,229,48,255),
                            backColor = Color(123,132,12,255))
                    }
                    else{
                        Text(text = "Mission in progress", color = Color.Red)
                        Spacer(modifier = Modifier.height(5.dp))
                        ProgressBar(monster.quantityCaptured, monster.totalToCapture,
                            modifier = Modifier.height(50.dp),
                            showNumbers = true,
                            frontColor = Color(223,32,32,255),
                            backColor = Color(111,9,9,255))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }

}

@Preview()
@Composable
fun HorizontalFileInformationPreview(){
    HorizontalFileInformation(1)
}