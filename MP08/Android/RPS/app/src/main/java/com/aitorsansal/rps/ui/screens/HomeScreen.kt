package com.aitorsansal.rps.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.rps.R


@Preview(showSystemUi = true)
@Composable
fun HomeScreen(onPlayClick : () -> Unit = {}, onSettingsClick : () -> Unit = {}){


    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(30.dp))
        Text("Rock Paper Scissors", style = MaterialTheme.typography.displaySmall)
        Text("Lizard  Spock", style = MaterialTheme.typography.displaySmall)
        Image(painter = painterResource(R.drawable.rps_banner), null, modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth().height(150.dp)) {
            Button(onClick = onPlayClick, modifier = Modifier.fillMaxSize().weight(1f).padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp)) {
                Image(painter = painterResource(R.drawable.play_button), null)
            }
            Button(onClick = onSettingsClick, modifier = Modifier.fillMaxSize().weight(1f).padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp)) {
                Image(painter = painterResource(R.drawable.settings), null)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }

}