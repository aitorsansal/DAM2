package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun ElementWeaknesses(givenWeakness:Map<String,Int>?, modifier : Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        var weakness = mapOf<String, Int>()
        weakness = if(givenWeakness == null){
            mapOf(
                "fire" to 0,
                "water" to 0,
                "thunder" to 0,
                "ice" to 0,
                "draco" to 0
            )
        } else
            givenWeakness
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.fire_element), contentDescription = null)
            var stars = weakness["Fire"] ?: 0
            for (i in 0 until stars) {
                Image(painter = painterResource(id = R.drawable.star),
                    colorFilter = ColorFilter.tint(Color(252,186,3)),
                    modifier = Modifier.size(25.dp).padding(horizontal = 1.dp),
                    contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.water_element), contentDescription = null)
            var stars = weakness["Water"] ?: 0
            for (i in 0 until stars) {
                Image(painter = painterResource(id = R.drawable.star),
                    colorFilter = ColorFilter.tint(Color(252,186,3)),
                    modifier = Modifier.size(25.dp).padding(horizontal = 1.dp),
                    contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.electric_element), contentDescription = null)
            var stars = weakness["Thunder"] ?: 0
            for (i in 0 until stars) {
                Image(painter = painterResource(id = R.drawable.star),
                    colorFilter = ColorFilter.tint(Color(252,186,3)),
                    modifier = Modifier.size(25.dp).padding(horizontal = 1.dp),
                    contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.ice_element), contentDescription = null)
            var stars = weakness["Ice"] ?: 0
            for (i in 0 until stars) {
                Image(painter = painterResource(id = R.drawable.star),
                    colorFilter = ColorFilter.tint(Color(252,186,3)),
                    modifier = Modifier.size(25.dp).padding(horizontal = 1.dp),
                    contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.draco_element), contentDescription = null)
            var stars = weakness["Draco"] ?: 0
            for (i in 0 until stars) {
                Image(painter = painterResource(id = R.drawable.star),
                    colorFilter = ColorFilter.tint(Color(252,186,3)),
                    modifier = Modifier.size(25.dp).padding(horizontal = 1.dp),
                    contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun PreviewElementWeakness(){
    ElementWeaknesses(mapOf(
        "Fire" to 1,
        "Water" to 2,
        "Thunder" to 3,
        "Ice" to 3,
        "Draco" to 1
    ))
}