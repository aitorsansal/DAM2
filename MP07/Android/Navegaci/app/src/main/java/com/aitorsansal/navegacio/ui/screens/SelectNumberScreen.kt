package com.aitorsansal.navegacio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectNumberScreen(onSendClick : (Int, Int) -> Unit, modifier : Modifier = Modifier){
    val range = remember { mutableStateOf(0f..100f) }

    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer)) {

        RangeSlider(
            value = range.value,
            onValueChange = { newValue -> range.value = newValue},
            valueRange = 0f..100f,
        )
        Box(modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.align(Alignment.CenterStart).padding(start = 10.dp),
                text = "Min: ${range.value.start.toInt()}")
            Text(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 10.dp),
                text = "Min: ${range.value.endInclusive.toInt()}")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {onSendClick(range.value.start.toInt(), range.value.endInclusive.toInt())}) {
            Text("Send")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectNumberScreen(){
    SelectNumberScreen({a :Int, b:Int ->})
}