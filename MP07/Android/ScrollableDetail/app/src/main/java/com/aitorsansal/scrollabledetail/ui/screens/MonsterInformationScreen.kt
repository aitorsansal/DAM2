package com.aitorsansal.scrollabledetail.ui.screens

import android.content.res.Configuration
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
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.monsterhunterapp.ui.composables.AlteredStatesWeaknesses
import com.aitorsansal.monsterhunterapp.ui.composables.ElementWeaknesses
import com.aitorsansal.scrollabledetail.R
import com.aitorsansal.scrollabledetail.data.dataRepository
import com.aitorsansal.scrollabledetail.model.Monster
import kotlin.collections.firstOrNull
import kotlin.text.split

@Composable
fun MonsterInformationScreen(
    id: String,
    modifier: Modifier = Modifier
){
    val monsters : List<Monster> = when (id.split("-")[0]){
        "MHWorld" -> dataRepository.MHWorldData
        "MHRise" -> dataRepository.MHRiseData
        "MH4" -> dataRepository.MH4UData
        "MHWilds" -> dataRepository.MHWildsData
        else -> {
            listOf()
        }
    }
    val monster = monsters.firstOrNull { it.id == id}
    val screenHalf = LocalConfiguration.current.screenWidthDp / 2
    Card(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.Companion.DarkGray)
                .padding(top = 30.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(monster?.image)
                        .size(150)
                        .build(), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally).width(150.dp).height(150.dp),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop
                )
                HorizontalDivider(thickness = 5.dp, color = Color.Companion.White)
                Text(
                    text = monster?.name
                        ?: "Wrong monster information", modifier = Modifier.fillMaxWidth(),
                    color = Color.Companion.White,
                    textAlign = TextAlign.Companion.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider(thickness = 5.dp, color = Color.Companion.White)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Element Weakness", textAlign = TextAlign.Companion.Center,
                        color = Color.Companion.White, modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Altered Stats Weakness", textAlign = TextAlign.Companion.Center,
                        color = Color.Companion.White, modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                HorizontalDivider(thickness = 2.dp, color = Color.Companion.White)
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ElementWeaknesses(
                        monster?.weakness,
                        modifier = Modifier.height(180.dp).width(screenHalf.dp)
                    )
                    AlteredStatesWeaknesses(
                        monster?.weaknessToAlteredStates,
                        modifier = Modifier.height(180.dp).width(screenHalf.dp)
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
                HorizontalDivider(thickness = 2.dp, color = Color.Companion.White)
                Text(
                    text = "Drops", textAlign = TextAlign.Companion.Center,
                    color = Color.Companion.White, modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
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