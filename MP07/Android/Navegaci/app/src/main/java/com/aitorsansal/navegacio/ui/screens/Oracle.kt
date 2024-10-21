package com.aitorsansal.navegacio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OracleScreen(onSendClick : (String) -> Unit, modifier : Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        var text by remember { mutableStateOf("") }
        Text(text = "Write a question for the oracle to answer.",
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = {text = it},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            minLines = 10
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button( onClick = {onSendClick(text)}) {
            Text(text = "Send")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewOracleScreen(){
    OracleScreen({})
}