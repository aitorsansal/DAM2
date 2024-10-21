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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen (onCoinFlipClick : () -> Unit, onGetRandomNumberClick : () -> Unit, onOracleClick : () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    )
    {
        Button(onClick = onCoinFlipClick ) {
            Text (
                text= "Coin flip", Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onGetRandomNumberClick ) {
            Text (
                text= "Get a number", Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onOracleClick ) {
            Text (
                text= "Oracle", Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewStartScreen(){
    FirstScreen({},{},{})
}