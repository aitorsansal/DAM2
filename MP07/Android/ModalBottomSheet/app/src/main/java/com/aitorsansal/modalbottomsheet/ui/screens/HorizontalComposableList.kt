package com.aitorsansal.modalbottomsheet.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.modalbottomsheet.R
import com.aitorsansal.modalbottomsheet.ui.composables.HorizontalFileInformation
import com.aitorsansal.modalbottomsheet.data.dataRepository
import com.aitorsansal.modalbottomsheet.model.Monster


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalComposableList(data : List<Monster>,
                             onClickElement : (String) -> Unit = {},
                             paddingValues : PaddingValues = PaddingValues(0.dp),
                             elementWidth : Dp = 500.dp
){
    var showModalSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var currentElement by remember {mutableStateOf("")}
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Companion.DarkGray)
            .padding(16.dp)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        itemsIndexed(data)
        {index, value ->
            HorizontalFileInformation(
                monsterId = value.id,
                onClickElement = { showModalSheet = true;
                                 currentElement = value.id},
                modifier = Modifier
                    .width(elementWidth)
                    .height(100.dp)
            )
        }
    }
    if(showModalSheet){
        ModalBottomSheet(onDismissRequest = {showModalSheet = false}, sheetState = sheetState) {
            var monster = dataRepository.getMonsterById(currentElement)
            if(monster != null){

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(monster.name)
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(monster.image)
                            .size(250)
                            .build(), contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 10.dp),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column {
                        Text(text = "Max Size: " + monster.maxSize)
                        Text(text = "Min Size: " + monster.minSize)
                    }

                }
                Button({
                    onClickElement(monster.id)
                }) {
                    Text("Go to the information →")
                }

            }
            else{
                Text("Incorrect Monster ID")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHorizontalComposableList(){
    dataRepository.obtainData(LocalContext.current)
    HorizontalComposableList(data = dataRepository.MHRiseData)
}
