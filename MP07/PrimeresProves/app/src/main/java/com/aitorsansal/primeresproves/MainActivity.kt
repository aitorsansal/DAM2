package com.aitorsansal.primeresproves

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Calculadora", fontSize = 20.sp, textAlign = TextAlign.Center) }
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Imatge", fontSize = 20.sp, textAlign = TextAlign.Center) }
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Formulari", fontSize = 20.sp, textAlign = TextAlign.Center) }
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Tria de colors", fontSize = 20.sp, textAlign = TextAlign.Center) }
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Columnes d'articles", fontSize = 20.sp, textAlign = TextAlign.Center) }
        Button( {} , modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1F).padding(10.dp),
            shape = RoundedCornerShape(5.dp)) {
                Text("Columnes d'articles amb molts botons", fontSize = 20.sp, textAlign = TextAlign.Center) }
    }

}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Calculadora(){
    Row (modifier = Modifier
            .fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer).padding(25.dp)){
        Column (modifier = Modifier.weight(3F).fillMaxWidth()) {
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("7", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("8", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("9", fontSize = 16.sp)}
            }
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("4", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("5", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("6", fontSize = 16.sp)}
            }
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("1", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("2", fontSize = 16.sp)}
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight().padding(5.dp), shape = RoundedCornerShape(5.dp)) {Text("3", fontSize = 16.sp)}
            }
            Row(modifier = Modifier.weight(1F).padding(10.dp)){
                Button ({}, modifier = Modifier.weight(1F).fillMaxHeight(), shape = RoundedCornerShape(5.dp)) {Text("0", fontSize = 16.sp)}
            }

        }
        Column (modifier = Modifier.weight(1F).fillMaxHeight()) {
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight(), shape = RoundedCornerShape(5.dp)) {Text("+", fontSize = 16.sp)}
            }
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight(), shape = RoundedCornerShape(5.dp)) {Text("C", fontSize = 16.sp)}
            }
            Row (modifier = Modifier.weight(1F).padding(10.dp)) {
                Button ( {}, modifier = Modifier.weight(1f).fillMaxHeight(), shape = RoundedCornerShape(5.dp)) {Text("=", fontSize = 16.sp)}
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Imatge(){
    Column{
        Row(modifier = Modifier.weight(1F)){
            Image(painter = painterResource(id = R.drawable.microsoft_wpf),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop)
        }
        Row(modifier = Modifier.weight(1F).padding(16.dp).padding(bottom = 35.dp).verticalScroll(rememberScrollState())){
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
                    " Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"+
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
                    " Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Formulari(){
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        TextField(value="Nom(*)", onValueChange = {}, label = {Text("Nom")},
            modifier = Modifier.fillMaxWidth().weight(1F).fillMaxHeight())
        TextField(value="Cognoms(*)", onValueChange = {}, label = {Text("Cognoms")},
            modifier = Modifier.fillMaxWidth().weight(1F).fillMaxHeight())
        TextField(value="Correu(*)", onValueChange = {}, label = {Text("Correu")},
            modifier = Modifier.fillMaxWidth().weight(1F).fillMaxHeight())
        TextField(value="Missatge(*)", onValueChange = {}, label = {Text("Missatge")},
            modifier = Modifier.fillMaxWidth().weight(3F).fillMaxHeight())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = true,
                onCheckedChange = {})
            Text(text = "Subscriu-me per correu")
        }
        TextField(value="Web del servidor", onValueChange = {}, label = {Text("Web servidor")},
            modifier = Modifier.fillMaxWidth().weight(1F).fillMaxHeight())
        Row(modifier = Modifier.fillMaxHeight().weight(1F).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Button({}){
                Text("Anul·la")
            }
            Button({}){
                Text("Confirma")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TriaDeColors(){
    Column(){
        Row(modifier = Modifier.height(140.dp).fillMaxHeight()){
            Column(modifier = Modifier.weight(3.5F).fillMaxWidth()){
                TextField(value="Paraula max 20 lletres", onValueChange = {}, label = {Text("Paraula")},
                    modifier = Modifier.fillMaxWidth().weight(3F).fillMaxHeight())
                TextField(value="Paraula max 20 lletres", onValueChange = {}, label = {Text("Traducció")},
                    modifier = Modifier.fillMaxWidth().weight(3F).fillMaxHeight())
            }
            Button({},modifier = Modifier.padding(5.dp).fillMaxHeight(),
                shape = RoundedCornerShape(5.dp)
            ){
                Text("Esborra")
            }
        }
        Row(modifier = Modifier.weight(4F).fillMaxHeight()){
            Column(modifier = Modifier.padding(16.dp)
                .background(color = Color.Black).padding(16.dp)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text("Afegeix - Edita - Esborra", color = Color.White, fontSize = 10.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Column(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp).fillMaxSize()
                    .verticalScroll(rememberScrollState()),){
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF658DC),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Rosa")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F9F9F),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Gris")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Blanc")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBBFB96),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Verd")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFbcf1f0),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Blau Clar")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFfffb00),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Groc")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFff0000),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Vermell")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF830077),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Lila")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe5d7e4),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Lavanda")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFf6eac4),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Prèsec")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF20029b),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Blau Fosc")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFff35f9),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Fucsia")
                    }
                    Button({},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35ffd7),
                                                            contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Turquesa")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Ex6(){
    Column(){
        Text("Sisè exercici", color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
            .background(color = Color.Black).padding(5.dp))
    }
}