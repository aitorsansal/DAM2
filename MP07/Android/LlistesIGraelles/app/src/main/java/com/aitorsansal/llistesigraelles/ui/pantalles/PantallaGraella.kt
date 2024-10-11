package com.aitorsansal.llistesigraelles.ui.pantalles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.aitorsansal.llistesigraelles.R
import com.aitorsansal.llistesigraelles.dades.repositoryFake
import com.aitorsansal.llistesigraelles.model.Guerrer

@Preview
@Composable
fun Graella(data : List<Guerrer> = repositoryFake.obtainData(100),
            onClickWarrior : (Int) -> Unit = {}){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(data){
            Column(){
                AsyncImage(model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(it.foto)
                    .build(),
                    modifier = Modifier.width(50.dp).height(50.dp),
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop)
                Text(it.nom)
            }
        }
    }
}