package com.aitorsansal.monsterhunterapp.ui.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aitorsansal.monsterhunterapp.R
import kotlin.math.absoluteValue

@Composable
fun AlteredStatesWeaknesses(givenWeakness:Map<String,Int>?, modifier : Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        var weakness = mapOf<String, Int>()
        weakness = if(givenWeakness == null){
            mapOf(
                "Poison" to 0,
                "Sleep" to 0,
                "Paralysis" to 0,
                "Blast" to 0,
                "Stun" to 0
            )
        } else
            givenWeakness
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.posion_element), contentDescription = null)
            var stars = weakness["Poison"] ?: 0
            var txt = ""
            for (i in 0 until stars) {
                txt += "⭐"
            }
            Text(text = txt)
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.sleep_element), contentDescription = null)
            var stars = weakness["Sleep"] ?: 0
            var txt = ""
            for (i in 0 until stars) {
                txt += "⭐"
            }
            Text(text = txt)
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.paralysis_element), contentDescription = null)
            var stars = weakness["Paralysis"] ?: 0
            var txt = ""
            for (i in 0 until stars) {
                txt += "⭐"
            }
            Text(text = txt)
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.blast_element), contentDescription = null)
            var stars = weakness["Blast"] ?: 0
            var txt = ""
            for (i in 0 until stars) {
                txt += "⭐"
            }
            Text(text = txt)
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.stun_element), contentDescription = null)
            var stars = weakness["Stun"] ?: 0
            var txt = ""
            for (i in 0 until stars) {
                txt += "⭐"
            }
            Text(text = txt)
        }
    }
}

@Preview
@Composable
fun PreviewAlteredStatesWeakness(){
    AlteredStatesWeaknesses(mapOf(
        "Poison" to 1,
        "Sleep" to 3,
        "Paralysis" to 2,
        "Blast" to 1,
        "Stun" to 0
    ))
}