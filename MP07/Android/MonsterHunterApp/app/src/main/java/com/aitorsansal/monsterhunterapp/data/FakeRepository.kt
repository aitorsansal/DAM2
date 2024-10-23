package com.aitorsansal.monsterhunterapp.data

import android.content.Context
import com.aitorsansal.monsterhunterapp.model.Monster
import com.aitorsansal.monsterhunterapp.model.MonsterList
import com.google.gson.Gson
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

object fakeRepository{


    public var MHWorldData : List<Monster> = listOf()
        private set

    public var MHRiseData : List<Monster> = listOf()
                private set

    public var MH4UData : List<Monster> = listOf()
                private set


    @OptIn(ExperimentalSerializationApi::class)
    public fun obtainData(context: Context)  {
        try {
            var inputStream = context.assets.open("MHWorldMonsters.json")
            MHWorldData = loadJson(inputStream)
            inputStream = context.assets.open("MHRiseMonsters.json")
            MHRiseData = loadJson(inputStream)
            inputStream = context.assets.open("MH4UMonsters.json")
            MH4UData = loadJson(inputStream)
        }
        catch (e : Exception){

        }

    }

    fun loadJson(jsonFileStream: InputStream) : List<Monster>{
        var monsters : List<Monster> = listOf()
        try {
            val gson = Gson()
            val reader = BufferedReader(InputStreamReader(jsonFileStream))

            monsters = gson.fromJson(reader, MonsterList::class.java).monsters

            jsonFileStream.close()
        }
        catch (e : Exception){
            print(e.message)
        }
        return monsters
    }

    fun GetMonsterList(id:String) : List<Monster>
    {
        return when (id) {
            "MHWorld" -> MHWorldData
            "MH4U" -> MH4UData
            "MHRise" -> MHRiseData
            else -> listOf<Monster>()
        }
    }


//    fun GenerateMonster(pos: Int) : Monster
//    {
//        val nOfDrops = Random.nextInt(1,10)
//        val dropsList : MutableList<String> = mutableListOf()
//        for (i in 1..nOfDrops)
//        {
//            dropsList.add(getRandomDrop(dropsList)?.first ?: "")
//        }
//        return Monster(
//            id = pos + 1,
//            name = monsterNames[pos],
//            image = imagesLinks[pos],
//            hp = Random.nextInt(1,10),
//            strength = Random.nextInt(1,10),
//            speed = Random.nextInt(1,10),
//            quantityCaptured = Random.nextInt(0,1000),
//            totalToCapture = Random.nextInt(100,1000),
//            drops = dropsList
//        )
//    }

    fun getRandomDrop(usedItems : MutableList<String>): Pair<String, String>? {
        // Get a list of unused items
        val unusedItems = drops.filterKeys { it !in usedItems }

        // Return null if all items are used
        if (unusedItems.isEmpty()) {
            return null
        }

        // Select a random entry from the unused items
        val randomEntry = unusedItems.entries.random()

        // Return the random entry (ID and link)
        return randomEntry.toPair()
    }


    val drops : Map<String,String> = mapOf<String,String>(
        "Dragonite Ore" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/22_2.png",
        "Dragonvein Crystal" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/22_1.png",
        "Monster Bone" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_4.png",
        "Monster Bone+" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_7.png",
        "Elder Dragon Bone" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_5.png",
        "Monster Claw" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/45_10.png",
        "Monster Hardclaw" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/45_11.png",
        "Monster Essence" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/5_3.png",
        "Monster Fluid" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/5_6.png",
        "Sinister Cloth" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/58_10.png",
        "Paralysis Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_9.png",
        "Sleep Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_3.png",
        "Toxin Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_5.png",
        "Flame Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_1.png",
        "Cryo Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_0.png",
        "Aqua Sac" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_6.png",
        "Monster Plate" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/61_14.png",
        "Monster Gem" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/9_14.png",
        "Monster Mantle" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/47_14.png",
        "Monster Wing" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/3_4.png",
        "Monster Carapace" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_7.png",
        "Monster Husk" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_14.png",
        "Monster Cortex" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_26.png",
        "Monster Scale" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/43_4.png",
        "Monster Shard" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/43_1.png",
        "Monster Plume" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/41_25.png",
        "Monster Beak" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/60_24.png",
        "Monster Tail" to "https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/52_7.png",
    )


    val monsterNames = mutableListOf(
        "Great Jagras",
        "Pukei-Pukei",
        "Kulu-Ya-Ku",
        "Tobi-Kadachi",
        "Barroth",
        "Jyuratodus",
        "Anjanath",
        "Fulgur Anjanath",
        "Odogaron",
        "Ebony Odogaron",
        "Rathalos",
        "Azure Rathalos",
        "Silver Rathalos",
        "Rathian",
        "Pink Rathian",
        "Gold Rathian",
        "Diablos",
        "Black Diablos",
        "Legiana",
        "Shrieking Legiana",
        "Barioth",
        "Frostfang Barioth",
        "Bazelgeuse",
        "Seething Bazelgeuse",
        "Deviljho",
        "Savage Deviljho",
        "Nergigante",
        "Ruiner Nergigante",
        "Vaal Hazak",
        "Blackveil Vaal Hazak",
        "Brachydios",
        "Raging Brachydios",
        "Zinogre",
        "Stygian Zinogre",
        "Paolumu",
        "Nightshade Paolumu",
        "Leshen",
        "Ancient Leshen",
        "Rajang",
        "Furious Rajang",
        "Yian Garuga",
        "Scarred Yian Garuga",
        "Tigrex",
        "Brute Tigrex",
        "Nargacuga",
        "Banbaro",
        "Tzitzi-Ya-Ku",
        "Radobaan",
        "Dodogama",
        "Beotodus",
        "Lavasioth",
        "Kushala Daora",
        "Teostra",
        "Xeno'jiiva",
        "Zorah Magdaros",
        "Behemoth",
        "Shara Ishvalda",
        "Namielle",
        "Kirin",
        "Kulve Taroth",
        "Safi'jiiva",
        "Velkhana",
        "Alatreon",
        "Fatalis",
        "Aknosom", //mhrise
        "Almudron",
        "Magma Almudron",
        "Arzuros",
        "Apex Arzuros",
        "Astalos",
        "Basarios",
        "Bishaten",
        "Blood Orange Bishaten",
        "Chameleos",
        "Risen Chameleos",
        "Daimyo Hermitaur",
        "Shogun Ceanataur",
        "Espinas",
        "Flaming Espinas",
        "Goss Harag",
        "Gaismagorm",
        "Garangolm",
        "Gore Magala",
        "Chaotic Gore Magala",
        "Shagu Magala",
        "Risen Shagu Magala",
        "Great Baggi",
        "Great Izuchi",
        "Great Wroggi",
        "Khezu",
        "Lagombi",
        "Lunagaron",
        "Magnamalo",
        "Scorned Magnamalo",
        "Mizutsune",
        "Violet Mitsuzune",
        "Apex Mizutsune",
        "Rakna-Kadaki",
        "Pyre Rakna-Kadaki",
        "Royal Ludroth",
        "Volvidon",
        "Somnacanth",
        "Aurora Somnacanth",
        "Seregios",
        "Tetranadon",
        "Wind Serpent Ibushi",
        "Thunder Serpent Narwa",
        "Narwa the Allmother",
        "Amatsu",
        "Crimson Glow Valstrax",
        "Malzeno",
        "Primordial Malzeno",
        "Risen Teostra",
        "Risen Kushala Daora",
        "Apex Rathalos",
        "Apex Rathian",
        "Apex Zinogre",
        "Apex Diablos",
    )

    val imagesLinks = mutableListOf(
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em101_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em102_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em107_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em109_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em044_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em108_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em100_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em100_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em113_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em113_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em002_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em002_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em002_02_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em001_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em001_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em001_02_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em007_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em007_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em111_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em111_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em042_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em042_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em118_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em118_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em043_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em043_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em103_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em103_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em115_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em115_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em063_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em063_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em057_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em057_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em110_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em110_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em127_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em127_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em023_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em023_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em018_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em018_05_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em032_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em032_01_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em037_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em123_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em120_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em114_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em116_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em122_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em036_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em024_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em027_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em105_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em106_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em121_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em126_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em125_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em011_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em117_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em104_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em124_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em050_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhworld-web/mhw/icon/em013_ID.png",
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em091_00.png", //aknosom
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em095_00.png", //almudron
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em095_01.png",//magma almudron
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em060_00.png", //arzuros
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em060_07.png", //apex arzuros
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em081_00.png", //Astalos
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em004_00.png", //basarios
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em090_00.png", //bishaten
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em090_01.png",//Blood Orange Bishaten
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em025_00.png", //Chameleos
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em025_08.png", //risen chameleos
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em019_00.png", //Daimyo Hermitaur
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em020_00.png",//Shogun Ceanataur
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em136_00.png", //Espinas
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em136_01.png",//Flaming espinas
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em097_00.png", //Goss Harag
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em135_00.png", //Gaismagorm
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em134_00.png", //Garangolm
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em071_00.png", //gore magala
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em071_05.png", //chaotic gore magala
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em072_00.png", //shagu magala
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em072_08.png", //risen shagu magala
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em054_00.png",//Great Baggi
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em098_00.png",//Great Izuchi
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em059_00.png", //great wroggi
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em003_00.png", //khezu
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em061_00.png", //Lagombi
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em133_00.png", //Lunagaron
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em089_00.png", //magnamalo
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em089_05.png",//Scorned Magnamalo
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em082_00.png", //mitsuzune
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em082_02.png", //Violet mitsuzune
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em082_07.png", //apex mizutsune
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em094_00.png",//Rakna-kadaki
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em094_01.png",//pyre rakna-kadaki
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em047_00.png",//Royal Ludroth
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em062_00.png", //Volvidon
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em093_00.png",//Somnacanth
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em093_01.png", //Variant somnacanth
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em077_00.png", //seregios
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em092_00.png",//Tetranadon
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em096_00.png",//Wind Serpent Ibushi
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em099_00.png",//Thunder Serpent Narwa
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em099_05.png",//Narwa the Allmother
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em058_00.png", //Amatsu
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em086_05.png", //valtrax
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em132_00.png",//malzeno
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em132_05.png", //primordial malzeno
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em027_08.png", //risen teostra
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em024_08.png", //risen kushala daora
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em002_07.png", //apex rathalos
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em001_07.png", //apex rathian
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em057_07.png", //apex zinogre
        "https://cdn.kiranico.net/file/kiranico/mhrise-web/images/icons/em007_07.png", //apex diablos
    )
}