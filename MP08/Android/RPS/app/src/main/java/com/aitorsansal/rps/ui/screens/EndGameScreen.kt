package com.aitorsansal.rps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aitorsansal.rps.viewModels.ViewModelEndGameScreen

@Preview(showSystemUi = true)
@Composable
fun EndGameScreen(viewModel : ViewModelEndGameScreen = viewModel()){
    Column (modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(25.dp))
        Text("GAME STATS", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Player Name", modifier = Modifier.weight(.6f).padding(horizontal = 30.dp))
            Text(viewModel.state.playerName,
                modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Won Enemies", modifier = Modifier.weight(.6f).padding(horizontal = 30.dp))
            Text(viewModel.state.enemiesDefeated.toString(),
                modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Won Rounds", modifier = Modifier.weight(.6f).padding(horizontal = 30.dp))
            Text(viewModel.state.wonRounds.toString(),
                modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Lost Rounds", modifier = Modifier.weight(.6f).padding(horizontal = 30.dp))
            Text(viewModel.state.lostRounds.toString(),
                modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }
}