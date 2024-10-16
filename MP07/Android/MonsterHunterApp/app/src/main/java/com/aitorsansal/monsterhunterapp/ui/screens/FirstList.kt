package com.aitorsansal.monsterhunterapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.model.Monster
import com.aitorsansal.monsterhunterapp.R

@Composable
fun FirstList(data : List<Monster>,
              onClickWarrior : (Int) -> Unit = {}){
    LazyColumn (modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(data)
        {
            ListItem(
                modifier = Modifier
                    .clickable { onClickWarrior(it.id) },
                colors = ListItemDefaults.colors(
                    containerColor = Color.DarkGray,
                    supportingColor = Color.White,
                    trailingIconColor = Color.White,

                ),
                headlineContent = {
                    Text(
                        it.name,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                },
                supportingContent = {
                    if(it.completedCatchingChallenge)
                        Text(
                            text = "Catch challenge:\n ${it.quantityCaptured }/${it.totalToCapture}",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Green,
                        )
                    else
                        Text(
                            text = "Catch challenge:\n ${it.quantityCaptured }/${it.totalToCapture}",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Yellow
                        )

                },
                trailingContent = { Text(text = "${it.id}") },
                leadingContent = {
                    AsyncImage(
                        model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(it.image)
                        .size(250)
                        .build(), contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewFirstList(){
    fakeRepository.obtainData()
    FirstList(fakeRepository.monsterData)
}