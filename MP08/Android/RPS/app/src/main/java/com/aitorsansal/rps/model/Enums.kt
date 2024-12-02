package com.aitorsansal.rps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
enum class PlayMode: Parcelable{
    Extended, Normal
}

enum class GameOptions{
    Rock, Paper, Scissors, Lizard, Spock
}

enum class Results{
    Win, Loose, Draw
}


fun PlayMode.toInt(): Int = when (this) {
    PlayMode.Normal -> 0
    PlayMode.Extended -> 1
}

fun Int.toPlayMode(): PlayMode = when (this) {
    0 -> PlayMode.Normal
    1 -> PlayMode.Extended
    else -> PlayMode.Normal // Fallback to default
}