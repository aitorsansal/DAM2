package com.aitorsansal.customlists.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun ProgressBar(
    current: Int,
    ending: Int,
    modifier : Modifier = Modifier,
    showNumbers: Boolean = false
){
    Row(modifier = modifier.fillMaxSize()){
        val fill = current.toFloat()/ending.toFloat()
        Box(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)){
            Box(modifier = Modifier.fillMaxWidth(fraction = fill)
                .fillMaxHeight().background(color = Color.LightGray)) {
            }
        }
        if(showNumbers)
        {
            Text(text = "$current/$ending")
        }
    }
}

@Preview(widthDp = 250, heightDp = 60, showBackground = true)
@Composable
fun PreviewProgressBar(){
    ProgressBar(20,100, showNumbers = true)
}