package cat.institutmontivi.decissorviewmodel.dades

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class Preferences(private val context : Context) {
    private val Context.dataStore by preferencesDataStore("preferences")
    private val TEMPS_ESPERA_CARA_O_CREU = longPreferencesKey("tempsCaraOCreu")
    private val TEMPS_ESPERA_NUMERO = longPreferencesKey("tempsNumero")
    private val MINIM_TRIA_NUMERO = intPreferencesKey("minimTriaNumero")
    private val MAXIM_TRIA_NUMERO = intPreferencesKey("maximTriaNumero")


    val getTempsCaraOCreu = context.dataStore.data.map { pref ->
        pref[TEMPS_ESPERA_CARA_O_CREU] ?: 0
    }
    suspend fun setTempsCaraOCreu(temps:Long){
        context.dataStore.edit { pref ->
            pref[TEMPS_ESPERA_CARA_O_CREU] = temps
        }
    }
    val getTempsNumero = context.dataStore.data.map { pref ->
        pref[TEMPS_ESPERA_NUMERO] ?: 0
    }
    suspend fun setTempsNumero(temps:Long){
        context.dataStore.edit { pref ->
            pref[TEMPS_ESPERA_NUMERO] = temps
        }
    }
    val getMinNum = context.dataStore.data.map { pref ->
        pref[MINIM_TRIA_NUMERO] ?: 0
    }
    suspend fun setMinNum(min:Int){
        context.dataStore.edit { pref ->
            pref[MINIM_TRIA_NUMERO] = min
        }
    }
    val getMaxNum = context.dataStore.data.map { pref ->
        pref[MAXIM_TRIA_NUMERO] ?: 100
    }
    suspend fun setMaxNum(max:Int){
        context.dataStore.edit { pref ->
            pref[MAXIM_TRIA_NUMERO] = max
        }
    }
}