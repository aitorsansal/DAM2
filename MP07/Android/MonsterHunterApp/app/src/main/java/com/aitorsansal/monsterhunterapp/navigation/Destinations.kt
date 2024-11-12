package com.aitorsansal.monsterhunterapp.navigation

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.core.content.ContextCompat
import kotlinx.serialization.Serializable
import com.aitorsansal.monsterhunterapp.R

@Serializable
object MonsterHunterWorld

@Serializable
object MonsterHunterRise

@Serializable
object MonsterHunter4Ultimate

@Serializable
object MonsterHunterWilds

@Serializable
object MHWorldList
@Serializable
object MHRiseList
@Serializable
object MH4UList
@Serializable
object MHWildsList
@Serializable
data class MonsterInformation(val id : String)

data class NavigationCategories<T:Any>(
    val route : T,
    val icon : Int,
    val title: String
)

val navigationCategories = listOf(
    NavigationCategories<MonsterHunterWorld>(
        route = MonsterHunterWorld,
        icon = R.drawable.mhworld_icon,
        title = "MHWorld"
    ),
    NavigationCategories<MonsterHunterRise>(
        route = MonsterHunterRise,
        icon = R.drawable.mhrise_icon,
        title = "MHRise"
    ),
    NavigationCategories<MonsterHunter4Ultimate>(
        route = MonsterHunter4Ultimate,
        icon = R.drawable.mh4u_icon,
        title = "MH4"
    )
)

