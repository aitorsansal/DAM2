package com.aitorsansal.monsterhunterapp.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aitorsansal.monsterhunterapp.R
import kotlinx.coroutines.launch


@Composable
fun CustomDrawerItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    colors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
){
    Box(modifier = modifier.fillMaxSize()){
        if(selected)
            Image(painter = painterResource(R.drawable.complete_mark_horizontal_selected),
                contentDescription = null,
                modifier = Modifier.fillMaxSize())
        else
            Image(painter = painterResource(R.drawable.complete_mark_horizontal),
                contentDescription = null,
                modifier = Modifier.fillMaxSize())


        Row(modifier = Modifier.fillMaxWidth().clickable{onClick()}.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(16.dp))
            if(icon != null) {
                Image(
                    painter = icon, null,
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    contentScale = ContentScale.Fit
                )
            }

            Text(text = label,
                modifier = Modifier.weight(3f),
                textAlign = TextAlign.Center,
                fontSize = 26.sp,
                color = colors.textColor(selected).value)
        }
    }
}

@Preview
@Composable
fun prev(){
    CustomDrawerItem("Testing", true, {},
        modifier = Modifier.height(100.dp).width(350.dp))
}