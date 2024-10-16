package com.aitorsansal.customlists.data

import com.aitorsansal.customlists.model.Monster
import kotlin.random.Random

object fakeRepository{


    public var monsterData : List<Monster> = listOf()
                private set


    public fun obtainData()  {
        if(monsterData.isEmpty())
            monsterData = (0..<monsterNames.size).toList().map{GenerateMonster(it) }.toList()
    }

    fun GenerateMonster(pos: Int) : Monster
    {
        val nOfDrops = Random.nextInt(1,10)
        val dropsList : MutableList<String> = mutableListOf()
        val doneNumbers : MutableList<Int> = mutableListOf()
        for (i in 1..nOfDrops)
        {
            var ind = Random.nextInt(0, drops.size)
            while (doneNumbers.contains(ind))
                ind = Random.nextInt(0,drops.size)
            dropsList.add(drops[ind])
        }
        return Monster(
            id = pos + 1,
            name = monsterNames[pos],
            image = imagesLinks[pos],
            hp = Random.nextInt(1,10),
            strength = Random.nextInt(1,10),
            speed = Random.nextInt(1,10),
            quantityCaptured = Random.nextInt(0,1000),
            totalToCapture = Random.nextInt(100,1000),
            drops = dropsList
        )
    }

    val drops = mutableListOf(
        "Dragonite Ore;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/22_2.png",
        "Dragonvein Crystal;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/22_1.png",
        "Monster Bone;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_4.png",
        "Monster Bone+;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_7.png",
        "Elder Dragon Bone;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/42_5.png",
        "Monster Claw;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/45_10.png",
        "Monster Hardclaw;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/45_11.png",
        "Monster Essence;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/5_3.png",
        "Monster Fluid;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/5_6.png",
        "Sinister Cloth;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/58_10.png",
        "Paralysis Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_9.png",
        "Sleep Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_3.png",
        "Toxin Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_5.png",
        "Flame Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_1.png",
        "Cryo Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_0.png",
        "Aqua Sac;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/13_6.png",
        "Monster Plate;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/61_14.png",
        "Monster Gem;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/9_14.png",
        "Monster Mantle;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/47_14.png",
        "Monster Wing;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/3_4.png",
        "Monster Carapace;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_7.png",
        "Monster Husk;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_14.png",
        "Monster Cortex;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/46_26.png",
        "Monster Scale;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/43_4.png",
        "Monster Shard;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/43_1.png",
        "Monster Plume;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/41_25.png",
        "Monster Beak;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/60_24.png",
        "Monster Tail;https://cdn.kiranico.net/file/kiranico/mhworld-web/images/itm/icon/52_7.png",
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