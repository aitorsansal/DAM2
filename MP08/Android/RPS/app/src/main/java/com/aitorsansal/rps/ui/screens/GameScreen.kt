package com.aitorsansal.rps.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aitorsansal.rps.model.PlayMode
import com.aitorsansal.rps.viewModels.ViewModelGameScreen


@Preview(showSystemUi = true)
@Composable
fun GameScreen(viewModel : ViewModelGameScreen = viewModel()){
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button({}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Rock", style = MaterialTheme.typography.headlineSmall)
            }
            Button({}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Paper", style = MaterialTheme.typography.headlineSmall)
            }
            Button({}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                shape = RoundedCornerShape(size = 5.dp)) {
                Text(text = "Scissors", style = MaterialTheme.typography.headlineSmall)
            }
        }
        if(viewModel.state.playMode == PlayMode.Normal){
            Row(modifier = Modifier.fillMaxWidth()) {
                Button({}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    shape = RoundedCornerShape(size = 5.dp)) {
                    Text(text = "Lizard", style = MaterialTheme.typography.headlineSmall)
                }
                Button({}, modifier = Modifier.weight(1f).height(100.dp).padding(10.dp),
                    shape = RoundedCornerShape(size = 5.dp)) {
                    Text(text = "Spock", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}