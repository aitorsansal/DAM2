package com.aitorsansal.navegacio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun OracleResponseScreen(question:String, modifier : Modifier = Modifier){
    val responses = listOf<String>(
        "Maybe",
        "Sure",
        "Why not",
        "No",
        "Absolutely",
        "Stop thinking that",
        "Idk",
        "Could be",
        "I don't think so"
    )
    Column(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var response : String = if(question == "") "Not enough data"
        else responses[Random.nextInt(0,responses.size)]
        Text("Your question:")
        Box(modifier = Modifier.padding(10.dp).fillMaxWidth()
            .border(2.dp, Color.Black)
            .padding(25.dp)){
            Text(
                text = question,
                style = MaterialTheme.typography.bodyLarge,
                minLines = 10
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Oracle's response:")
        Box(modifier = Modifier.padding(10.dp)
            .border(2.dp, Color.Black)
            .padding(25.dp)){
            Text(response)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOracleResponseScreen(){
    OracleResponseScreen("Should I play MHW and not go to class for a whole week?")
}