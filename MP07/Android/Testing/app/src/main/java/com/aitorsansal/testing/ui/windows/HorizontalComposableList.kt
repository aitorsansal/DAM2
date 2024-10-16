package com.aitorsansal.customlists.ui.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.customlists.composables.HorizontalFileInformation
import com.aitorsansal.customlists.data.fakeRepository
import com.aitorsansal.customlists.model.Monster


@Composable
fun HorizontalComposableList(data : List<Monster>,
              onClickElement : (Int) -> Unit = {}){
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray)
        .padding(16.dp)
        .padding(vertical = 25.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(data)
        {
            HorizontalFileInformation(it.id-1, onClickElement = onClickElement)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHorizontalComposableList(){
    fakeRepository.obtainData()
    HorizontalComposableList(fakeRepository.monsterData)
}
