package com.aitorsansal.navegacio.navigation

import kotlinx.serialization.Serializable

@Serializable
object StartScreen

@Serializable
object CoinFlip

@Serializable
object SelectNumber

@Serializable
data class SelectedNumber(val start:Int, val end:Int)

@Serializable
object Oracle

@Serializable
data class OracleResponse(val question:String)