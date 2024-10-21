package com.aitorsansal.navegacio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun CoinFlipScreen(modifier : Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer))
    {
        if(Random.nextInt(0,2) == 1)
            Icon(imageVector = Icons.Default.Circle, contentDescription = null,
                modifier = Modifier.align(Alignment.Center).size(250.dp))
        else
            Icon(imageVector = Icons.Filled.Close, contentDescription = null,
                modifier = Modifier.align(Alignment.Center).size(250.dp))

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCoinFlipScreen(){
    CoinFlipScreen()
}