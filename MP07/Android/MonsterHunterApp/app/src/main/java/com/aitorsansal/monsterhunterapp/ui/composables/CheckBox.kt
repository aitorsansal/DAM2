package com.aitorsansal.monsterhunterapp.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomCheck(checked: Boolean, color : Color = Color.Red, modifier : Modifier = Modifier)
{
    if(checked)
    {
        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = color, modifier = modifier)
    }
    else
        Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = color, modifier = modifier)
}