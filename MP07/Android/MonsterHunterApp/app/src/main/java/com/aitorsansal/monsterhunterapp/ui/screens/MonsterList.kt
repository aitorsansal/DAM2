package com.aitorsansal.monsterhunterapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aitorsansal.monsterhunterapp.ui.composables.VerticalFileInformation
import com.aitorsansal.monsterhunterapp.R
import com.aitorsansal.monsterhunterapp.model.Monster


@Composable
fun MonsterList(monsterData : List<Monster>,
                onClickElement : (String) -> Unit = {},
                gridState:LazyGridState = rememberLazyGridState(),
                showBackground : Boolean = true){
    if(showBackground)
        Image(painter = painterResource(R.drawable.pergamin_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())

    LazyVerticalGrid (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .padding(vertical = 25.dp),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Adaptive(minSize = 125.dp)
    ) {
        items(monsterData)
        {
            var id : String? = it.id
            VerticalFileInformation(id,monsterData, onClickElement = { onClickElement((id).toString())}, modifier = Modifier.size(250.dp))
        }
    }

}