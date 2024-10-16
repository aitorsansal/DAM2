package com.aitorsansal.monsterhunterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import com.aitorsansal.monsterhunterapp.data.fakeRepository
import com.aitorsansal.monsterhunterapp.ui.screens.CustomGridList
import com.aitorsansal.monsterhunterapp.ui.screens.MonsterInformation
import com.aitorsansal.monsterhunterapp.ui.theme.MonsterHunterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterHunterAppTheme {
                fakeRepository.obtainData()
                var currentView by remember{ mutableStateOf(ViewState.Grid) }
                var monsterId by remember { mutableIntStateOf(1) }
                val gridState = rememberLazyGridState()


                when(currentView){
                    ViewState.Grid -> CustomGridList(onClickElement = {monsterId = it; currentView = ViewState.Information}, gridState = gridState, data = fakeRepository.monsterData)
                    ViewState.Information -> MonsterInformation(monsterId, onClickElement = {currentView = ViewState.Grid})
                }
            }
        }
    }
}

enum class ViewState {
    Grid,
    Information
}