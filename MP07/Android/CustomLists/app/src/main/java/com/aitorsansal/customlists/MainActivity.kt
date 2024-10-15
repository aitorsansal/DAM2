package com.aitorsansal.customlists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                CustomGridList()
            }
        }
    }
}