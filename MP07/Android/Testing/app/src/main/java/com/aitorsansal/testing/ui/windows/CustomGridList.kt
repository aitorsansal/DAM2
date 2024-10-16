package com.aitorsansal.customlists.ui.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.customlists.composables.VerticalFileInformation
import com.aitorsansal.customlists.data.fakeRepository
import com.aitorsansal.customlists.model.Monster


@Composable
fun CustomGridList(data : List<Monster>,
                   onClickElement : (Int) -> Unit = {},
                   gridState:LazyGridState){
    LazyVerticalGrid (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray)
        .padding(16.dp)
        .padding(vertical = 25.dp),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Adaptive(minSize = 125.dp)
    ) {
        items(data)
        {
            VerticalFileInformation(it.id-1, onClickElement = onClickElement, modifier = Modifier.height(250.dp))
        }
    }
}

@Preview
@Composable
fun PreviewCustomList(){
    fakeRepository.obtainData()
    CustomGridList(gridState = rememberLazyGridState(), data = fakeRepository.monsterData)
}