package com.aitorsansal.monsterhunterapp.model

class Monster(
    val id : String?,
    val name : String,
    val minSize: Double,
    val maxSize: Double,
    val weakness : Map<String,Int>,
    val weaknessToAlteredStates : Map<String,Int>
)


data class MonsterList(val monsters:List<Monster>)