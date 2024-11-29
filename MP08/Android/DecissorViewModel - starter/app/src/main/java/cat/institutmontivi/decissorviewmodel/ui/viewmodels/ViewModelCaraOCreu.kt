package cat.institutmontivi.decissorviewmodel.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelCaraOCreu : ViewModel() {

    var estat by mutableStateOf(EstatCaraOCreu())
        private set
    init{
        estat = EstatCaraOCreu(
            estaSortejant = false,
            resultat = 0
        )
    }

    fun sorteja(time: Long) {
        estat = estat.copy(
            estaSortejant = true,
            resultat = 0
        )
        viewModelScope.launch{
            delay(time)
            estat = estat.copy(
                estaSortejant = false,
                resultat = (1..2).random()
            )
        }


    }

}

data class EstatCaraOCreu(
    val estaSortejant : Boolean = false,
    val resultat : Int = 0
)