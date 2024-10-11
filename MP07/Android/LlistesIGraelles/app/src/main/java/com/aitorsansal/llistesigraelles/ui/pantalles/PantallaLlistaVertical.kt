package com.aitorsansal.llistesigraelles.ui.pantalles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.aitorsansal.llistesigraelles.R
import com.aitorsansal.llistesigraelles.dades.repositoryFake
import com.aitorsansal.llistesigraelles.model.Guerrer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyCol(data : List<Guerrer> = repositoryFake.obtainData(100),
            onClickWarrior : (Int) -> Unit = {}){
    LazyColumn (modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        stickyHeader {
            Text("Warriors list",
                modifier = Modifier.fillMaxWidth().background(Color.Red),
                style = MaterialTheme.typography.titleLarge)
        }
        items(data){
            ListItem(
                modifier = Modifier
                    .clickable { onClickWarrior(it.id) },
                colors = ListItemDefaults.colors(
                    containerColor = Color.DarkGray,
                    headlineColor = it.color,
                    supportingColor = Color.White,
                    trailingIconColor = Color.White),
                headlineContent = {Text(it.nom,
                    style = MaterialTheme.typography.bodySmall)},
                supportingContent = {Text(it.descripcio,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)},
                trailingContent = { Text(text = "${it.id}") },
                leadingContent = {
                    AsyncImage(model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(it.foto)
                        .build()
                        , contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop
                    )
                }
                )
            HorizontalDivider(color = Color.Red, thickness = 1.dp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview(){
    LazyCol()
}