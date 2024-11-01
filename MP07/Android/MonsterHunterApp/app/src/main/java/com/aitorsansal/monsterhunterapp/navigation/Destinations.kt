package com.aitorsansal.monsterhunterapp.navigation

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.core.content.ContextCompat
import kotlinx.serialization.Serializable

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
    val selectedIcon : ImageVector,
    val title: String
)

