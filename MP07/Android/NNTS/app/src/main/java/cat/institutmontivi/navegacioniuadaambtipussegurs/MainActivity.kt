package cat.institutmontivi.navegacioniuadaambtipussegurs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cat.institutmontivi.navegacioniuadaambtipussegurs.ui.AppBarraDeNavegacio
import cat.institutmontivi.navegacioniuadaambtipussegurs.ui.AppDrawer
import cat.institutmontivi.navegacioniuadaambtipussegurs.ui.theme.NavegacioNiuadaAmbTipusSegursTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacioNiuadaAmbTipusSegursTheme {
                //AppBarraDeNavegacio()
                AppDrawer()
            }
        }
    }
}

