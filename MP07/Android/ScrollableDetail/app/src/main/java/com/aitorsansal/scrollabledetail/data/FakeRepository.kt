package com.aitorsansal.scrollabledetail.data

import android.content.Context
import com.aitorsansal.scrollabledetail.model.Monster
import com.aitorsansal.scrollabledetail.model.MonsterList
import com.google.gson.Gson
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object dataRepository{


    var MHWorldData : List<Monster> = listOf()
        private set

    var MHRiseData : List<Monster> = listOf()
                private set

    var MH4UData : List<Monster> = listOf()
                private set

    var MHWildsData : List<Monster> = listOf()
                private set


    fun obtainData(context: Context)  {
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
}