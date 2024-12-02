package com.aitorsansal.rps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.data.Preferences
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun PreferencesScreen(){

    val preferences = Preferences(LocalContext.current)
    val playerName by preferences.getPlayerName.collectAsState(initial = 0)
    val gameMode by preferences.getPlayMode.collectAsState(initial = 0)
    val quantityOfGames by preferences.getQuantityOfGamesToWin.collectAsState(initial = 0)

    val coroutineScope = rememberCoroutineScope()
    Column (
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Game Preferences",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)
        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .height(3.dp))
        Column (
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){

            Spacer(modifier = Modifier.height(16.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )) {
                Text(text = "Player Name",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center)
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp))
                TextField(
                    value = playerName.toString(),
                    onValueChange = { coroutineScope.launch{
                        preferences.setPlayerName(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )) {
                Text(text = "Game Mode",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center)
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text("Normal Mode", modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                    Switch(checked = gameMode == PlayMode.Extended,
                        onCheckedChange = {
                            coroutineScope.launch{
                                if(it)
                                    preferences.setPlayMode(PlayMode.Extended)
                                else
                                    preferences.setPlayMode(PlayMode.Normal)
                            }
                        },
                        modifier = Modifier.weight(1F))
                    Text("Extended Mode", modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )) {
                Text(text = "Best of ??",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center)
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text("Best of 3", modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                    Switch(checked = quantityOfGames == 3,
                        onCheckedChange = {
                            coroutineScope.launch{
                                if(it)
                                    preferences.setGamesToWin(3)
                                else
                                    preferences.setGamesToWin(2)
                            }
                        },
                        modifier = Modifier.weight(1F))
                    Text("Best of 5", modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}