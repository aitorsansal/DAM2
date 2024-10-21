package com.aitorsansal.navegacio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun ShowNumberScreen(start:Int, end:Int, modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer)){
        Text(Random.nextInt(start, end+1).toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 150.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewShowNumberScreen(){
    ShowNumberScreen(0,100)
}