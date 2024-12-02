package com.aitorsansal.rps.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aitorsansal.rps.model.PlayMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class Preferences(private val context : Context) {

    private val Context.dataStore by  preferencesDataStore("preferences")
    private val PLAYER_NAME = stringPreferencesKey("playerName")
    private val PLAY_MODE = intPreferencesKey("playMode")
    private val QUANTITY_OF_GAMES_TO_WIN = intPreferencesKey("quantityToWin")


    val getPlayerName = context.dataStore.data.map { pref ->
        pref[PLAYER_NAME] ?: ""
    }
    suspend fun setPlayerName(playerName:String){
        context.dataStore.edit { pref ->
            pref[PLAYER_NAME] = playerName
        }
    }
    val getPlayMode : Flow<PlayMode> = context.dataStore.data.map { pref ->
        when (pref[PLAY_MODE]) {
            0 -> PlayMode.Normal
            1 -> PlayMode.Extended
            else -> PlayMode.Normal // Provide a default value if the key is missing
        }
    }
    suspend fun setPlayMode(mode: PlayMode){
        context.dataStore.edit { pref ->
            if(mode == PlayMode.Normal)
                pref[PLAY_MODE] = 0
            else if(mode == PlayMode.Extended)
                pref[PLAY_MODE] = 1
        }
    }
    val getQuantityOfGamesToWin = context.dataStore.data.map { pref ->
        pref[QUANTITY_OF_GAMES_TO_WIN] ?: 3
    }
    suspend fun setGamesToWin(quantityOfGames:Int){
        context.dataStore.edit { pref ->
            pref[QUANTITY_OF_GAMES_TO_WIN] = quantityOfGames
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: Preferences? = null

        fun getInstance(context: Context): Preferences {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Preferences(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}