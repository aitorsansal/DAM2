package com.aitorsansal.primeresproves

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.primeresproves.ui.theme.PrimeresProvesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimeresProvesTheme {
                Greeting("hola")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.primary
    )
    Button({}) { }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StartWindow(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(40.dp)
        .background(MaterialTheme.colorScheme.tertiaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Calculadora") }
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Imatge") }
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Formulari") }
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Tria de colors") }
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Columnes d'articles") }
        Button( {} , modifier = Modifier.fillMaxWidth()) { Text("Columnes d'articles amb molts botons") }
    }

}

@SuppressLint("Range")
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Calculadora(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB47E22)), // Background color (brown-ish)
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // First Row
        Row {
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
        }
        // Second Row
        Row {
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
        }
        // Third Row
        Row {
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
            Button ( {}, modifier = Modifier.weight(1f)) {Text("1")}
        }
        // Last Row for "0"
        Row {
            Button (
                { }, modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()) {}
        }
    }



}