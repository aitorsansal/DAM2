package com.aitorsansal.llistesigraelles.model

import androidx.compose.ui.graphics.Color

data class Guerrer(
    val id: Int,
    val nom: String,
    val foto: String,
    val color: Color,
    val descripcio: String,
    val velocitat: Int,
    val vida: Int,
    val atac: Int,
    val defensa: Int
)