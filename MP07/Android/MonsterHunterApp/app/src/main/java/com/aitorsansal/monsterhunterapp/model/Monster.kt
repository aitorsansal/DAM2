package com.aitorsansal.monsterhunterapp.model

class Monster(
    val id : Int,
    val name : String,
    val image : String,
    val hp : Int,
    val strength : Int,
    val speed : Int,
    val quantityCaptured : Int,
    val totalToCapture : Int,
    val drops : MutableList<String> //split with ";" first part is the name. second part is the image
){
    public var completedCatchingChallenge = quantityCaptured >= totalToCapture
}