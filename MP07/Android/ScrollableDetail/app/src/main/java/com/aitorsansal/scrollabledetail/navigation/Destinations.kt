package com.aitorsansal.scrollabledetail.navigation

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.core.content.ContextCompat
import com.aitorsansal.scrollabledetail.R
import kotlinx.serialization.Serializable

@Serializable
object MHWorldListNav
@Serializable
object MHRiseListNav
@Serializable
object MH4UListNav

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