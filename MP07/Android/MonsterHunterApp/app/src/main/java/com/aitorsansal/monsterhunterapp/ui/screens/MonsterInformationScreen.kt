package com.aitorsansal.monsterhunterapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
import com.aitorsansal.monsterhunterapp.ui.composables.ItemBlock
import com.aitorsansal.monsterhunterapp.ui.composables.ProgressBar
import com.aitorsansal.monsterhunterapp.ui.composables.StarsProgressBar
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.R
import com.aitorsansal.monsterhunterapp.ui.composables.CustomCheck

@Composable
fun MonsterInformationScreen(
    id: String,
    modifier: Modifier = Modifier,
    onClickElement : () -> Unit = {}
){
    val monster = fakeRepository.MHRiseData[id.toInt()]
    Card(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray).padding(top = 30.dp)){
            Column(modifier = Modifier.fillMaxSize()) {
//                AsyncImage(
//                    model = ImageRequest
//                        .Builder(LocalContext.current)
//                        .data(monster.image)
//                        .size(150)
//                        .build(), contentDescription = null,
//                    modifier = Modifier.align(Alignment.CenterHorizontally).width(150.dp).height(150.dp),
//                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
//                    contentScale = ContentScale.Crop
//                )
                HorizontalDivider(thickness = 5.dp, color = Color.White)
                Text(text = monster.name, modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(thickness = 5.dp, color = Color.White)
                Text(text = "Stats", textAlign = TextAlign.Center,
                    color = Color.White, modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium)
                HorizontalDivider(thickness = 2.dp, color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                Spacer(modifier = Modifier.height(15.dp))
                HorizontalDivider(thickness = 2.dp, color = Color.White)
                Text(text = "Catch Challenge", textAlign = TextAlign.Center,
                    color = Color.White, modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium)
                HorizontalDivider(thickness = 2.dp, color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Completed challenge:", color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
                Spacer(modifier = Modifier.height(15.dp))
                HorizontalDivider(thickness = 2.dp, color = Color.White)
                Text(text = "Drops", textAlign = TextAlign.Center,
                    color = Color.White, modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium)
                HorizontalDivider(thickness = 2.dp, color = Color.White)
//                LazyVerticalGrid (modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                    horizontalArrangement = Arrangement.Absolute.Center,
//                    verticalArrangement = Arrangement.Center,
//                    columns = GridCells.Adaptive(minSize = 60.dp)) {
//                    items(monster.drops){
//                        Box(modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)){
//                            ItemBlock(it)
//                        }
//                    }
//                }

            }

        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PreviewMonsterInformation() {
//    fakeRepository.obtainData()
    MonsterInformationScreen("2")
}