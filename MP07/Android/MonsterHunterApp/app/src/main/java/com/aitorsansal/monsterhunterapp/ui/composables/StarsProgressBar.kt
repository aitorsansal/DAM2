package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aitorsansal.monsterhunterapp.R

@Composable
fun StarsProgressBar(
    valueToUse : Int,
    modifier: Modifier = Modifier,
    completeStar : Color = Color(255,230,123,255),
    incompleteStar : Color = Color(201,140,81,255)
){
    val completeStars = valueToUse/2
    var emptyStars = 5-completeStars
    Row(modifier = modifier.fillMaxWidth()) {
        for (i in 1..completeStars) {
            Image(painter = painterResource(id = R.drawable.star),
                colorFilter = ColorFilter.tint(completeStar),
                modifier = Modifier.size(60.dp).padding(horizontal = 1.dp),
                contentDescription = null)
        }
        if(valueToUse%2 != 0)
        {
            emptyStars--
            Row(modifier = Modifier.padding(horizontal = 1.dp)){
                Image(painter = painterResource(id = R.drawable.left_half_star),
                    colorFilter = ColorFilter.tint(completeStar),
                    modifier = Modifier.width(30.dp),
                    contentDescription = null)
                Image(painter = painterResource(id = R.drawable.right_half_star),
                    colorFilter = ColorFilter.tint(incompleteStar),
                    modifier = Modifier.width(30.dp),
                    contentDescription = null)
            }

        }
        for (i in 1..emptyStars) {
            Image(painter = painterResource(id = R.drawable.star),
                colorFilter = ColorFilter.tint(incompleteStar),
                modifier = Modifier.size(60.dp).padding(horizontal = 1.dp),
                contentDescription = null)
        }
    }
}