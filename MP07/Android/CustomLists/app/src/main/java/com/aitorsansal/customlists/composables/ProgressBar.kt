package com.aitorsansal.customlists.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    current: Int,
    ending: Int,
    modifier : Modifier = Modifier,
    showNumbers: Boolean = false,
    backColor : Color = Color(10,91,16,255),
    frontColor : Color = Color(151,251,158,255),
    textColor: Color = Color.White
){
    Row(modifier = modifier.fillMaxWidth().padding(5.dp)){
        val fill = current.toFloat()/ending.toFloat()
        Card(modifier = Modifier.fillMaxSize().weight(2F)) {
            Box(modifier = Modifier.background(color = backColor)){
                Box(modifier = Modifier.fillMaxWidth(fraction = fill)
                    .fillMaxHeight().background(color = frontColor)) {
                }
            }

        }
        if(showNumbers)
        {
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "$current/$ending",
                modifier = Modifier.fillMaxWidth()
                    .weight(.6f).align(Alignment.CenterVertically),
                color = textColor)
        }
    }
}

@Preview(widthDp = 250, heightDp = 60, showBackground = true)
@Composable
fun PreviewProgressBar(){
    ProgressBar(20,100, showNumbers = true)
}