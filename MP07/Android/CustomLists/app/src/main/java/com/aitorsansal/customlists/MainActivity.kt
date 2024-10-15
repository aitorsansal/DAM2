package com.aitorsansal.customlists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aitorsansal.customlists.composables.MonsterInformation
import com.aitorsansal.customlists.data.fakeRepository
import com.aitorsansal.customlists.ui.theme.CustomListsTheme
import com.aitorsansal.customlists.ui.windows.CustomGridList
import com.aitorsansal.customlists.ui.windows.FirstList
import com.aitorsansal.customlists.ui.windows.HorizontalComposableList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomListsTheme {
                fakeRepository.obtainData()
                var currentView by remember{ mutableStateOf(ViewState.Grid) }
                var monsterId by remember { mutableIntStateOf(1) }
                val gridState = rememberLazyGridState()


                when(currentView){
                    ViewState.Grid -> CustomGridList(onClickElement = {monsterId = it; currentView = ViewState.Information}, gridState = gridState)
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