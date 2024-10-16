package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.monsterhunterapp.R

@Composable
fun ItemBlock(
    item:String,
    modifier: Modifier = Modifier
){
    var showTooltip by remember { mutableStateOf(false) }
    val splited = item.split(";")
    Card() {
        Box(
            modifier = modifier.fillMaxSize().background(color = Color(31,31,31,255))
                .padding(5.dp)
                .clickable { showTooltip = !showTooltip } // Toggle tooltip on click
        ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(splited[1])
                        .build(), contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop
                )

            // Tooltip displayed conditionally
            if (showTooltip) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(Color.Gray)
                        .padding(8.dp)
                        .align(Alignment.TopCenter)
                        .fillMaxSize()
                ) {
                    Text(text = splited[0], color = Color.White)
                }
            }
        }
    }
}