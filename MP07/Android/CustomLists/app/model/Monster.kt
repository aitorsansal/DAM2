package com.aitorsansal.pokedexlist.model

class Monster(
    val id: Int,
    val name: String,
    val image: String,
    val hp: Int,
    val strength: Int,
    val speed: Int,
    val quantityCaptured: Int,
    val totalToCapture: Int,
){
    public var completedCatchChallenge = quantityCaptured >= totalToCapture
}