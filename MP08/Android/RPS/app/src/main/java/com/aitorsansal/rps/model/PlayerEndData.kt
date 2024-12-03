package com.aitorsansal.rps.model

import android.os.Parcelable
import androidx.navigation.NavHostController
import kotlinx.parcelize.Parcelize

@Parcelize
class PlayerEndData(var name:String, var wonEnemies : Int, var wonRounds : Int, var lostRounds : Int) : Parcelable {
}