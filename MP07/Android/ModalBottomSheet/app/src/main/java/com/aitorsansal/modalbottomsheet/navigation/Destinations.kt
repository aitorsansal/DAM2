package com.aitorsansal.modalbottomsheet.navigation

import com.aitorsansal.modalbottomsheet.R
import kotlinx.serialization.Serializable

@Serializable
object MHWorldListNav
@Serializable
object MHRiseListNav
@Serializable
object MH4UListNav
@Serializable
data class MonsterInformationNav(var monsterId : String)

data class NavigationCategories<T:Any>(
    val route : T,
    val icon : Int,
    val title: String
)

val navigationCategories = listOf(
    NavigationCategories<MHWorldListNav>(
        route = MHWorldListNav,
        icon = R.drawable.mhworld_icon,
        title = "MHWorld"
    ),
    NavigationCategories<MHRiseListNav>(
        route = MHRiseListNav,
        icon = R.drawable.mhrise_icon,
        title = "MHRise"
    ),
    NavigationCategories<MH4UListNav>(
        route = MH4UListNav,
        icon = R.drawable.mh4u_icon,
        title = "MH4"
    )
)