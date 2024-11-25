package com.aitorsansal.modalbottomsheet.model

class Monster(
    val id : String,
    val name : String,
    val minSize: Double,
    val maxSize: Double,
    val weakness : Map<String,Int>,
    val weaknessToAlteredStates : Map<String,Int>,
    val image : String = ""
)


data class MonsterList(val monsters:List<Monster>)