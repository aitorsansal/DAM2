package com.aitorsansal.rps.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.aitorsansal.rps.model.GameOptions
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.viewModels.ViewModelGameScreen
import com.aitorsansal.rps.R


@Composable
fun GameScreen(navController : NavHostController, viewModel : ViewModelGameScreen = viewModel()){
    val navigationEvent by viewModel.navigationEvent.collectAsState(initial = null)
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let {
            navController.navigate(it){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                text = viewModel.state.currentRound)
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                text = viewModel.state.enemyName)
            Box(
                modifier = Modifier
                    .size(150.dp) // Specify the desired size
            ) {
                if (viewModel.state.enemySelection != null) {
                    Image(painter = painterResource(GetImg(viewModel.state.enemySelection)), null,
                        modifier = Modifier.fillMaxSize())
                }
            }
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                text = viewModel.state.currentResult)
            Box(
                modifier = Modifier
                    .size(150.dp)
            ) {
                if (viewModel.state.playerSelection != null) {
                    Image(painter = painterResource(GetImg(viewModel.state.playerSelection)), null,
                        modifier = Modifier.fillMaxSize())
                }
            }
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                text = viewModel.state.playerName)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button({viewModel.selectOption(GameOptions.Rock)}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Rock", style = MaterialTheme.typography.headlineSmall)
            }
            Button({viewModel.selectOption(GameOptions.Paper)}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Paper", style = MaterialTheme.typography.headlineSmall)
            }
            Button({viewModel.selectOption(GameOptions.Scissors)}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Scissors", style = MaterialTheme.typography.headlineSmall)
            }
        }
        if(viewModel.state.playMode == PlayMode.Extended){
            Row(modifier = Modifier.fillMaxWidth()) {
                Button({viewModel.selectOption(GameOptions.Lizard)}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    shape = RoundedCornerShape(size = 5.dp)) {
                    Text(text = "Lizard", style = MaterialTheme.typography.headlineSmall)
                }
                Button({viewModel.selectOption(GameOptions.Spock)}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    shape = RoundedCornerShape(size = 5.dp)) {
                    Text(text = "Spock", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}


fun GetImg(opt : GameOptions?) : Int{
    return when (opt){
        GameOptions.Rock -> R.drawable.rock
        GameOptions.Paper -> R.drawable.paper
        GameOptions.Scissors -> R.drawable.scissors
        GameOptions.Lizard -> R.drawable.lizard
        GameOptions.Spock -> R.drawable.spock
        null -> 0
    }

}