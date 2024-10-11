package com.aitorsansal.llistesigraelles.dades

import androidx.compose.ui.graphics.Color
import com.aitorsansal.llistesigraelles.model.Guerrer
import kotlin.random.Random

object repositoryFake {


    private val Noms = listOf(
        "Joan",
        "Pere",
        "Conan",
        "Lopez",
    )

    private val Adjectius = listOf(
        "l'ivnasor",
        "l'assassí",
        "el destripador",
        "el maleducat",
        "el magnífic",
        "l'estelar",
        "el conqueridor"
    )


    private fun GenerateWarrior(num:Int) : Guerrer
    {
        val posName = Random.nextInt(0, Noms.size)
        val posAdj = Random.nextInt(0, Adjectius.size)
        return Guerrer(
            id = num,
            nom = "${Noms[posName]} ${Adjectius[posAdj]}",
            foto ="https://loremflickr.com/320/240/warrior?lock=${num}",
            color = Color(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256),
                255
            ),
            descripcio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            velocitat = Random.nextInt(1,100),
            vida = Random.nextInt(20, 151),
            atac = Random.nextInt(1, 16),
            defensa = Random.nextInt(1,11)
        )
    }

    public fun obtainData(quantity: Int)  = (1..quantity).toList().map{GenerateWarrior(it)}.toList()
}