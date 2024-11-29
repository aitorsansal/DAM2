package cat.institutmontivi.decissorviewmodel.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelTriaNumero : ViewModel() {

    var estat by mutableStateOf(EstatTriaNumero())
        private set
    init {
        estat = estat.copy(valorTriat = 0, estaSortejant = false)
    }

    fun Sorteja(min:Int, max:Int, temps:Long){
        estat = estat.copy(estaSortejant = true)
        viewModelScope.launch{
            delay(temps)
            estat = estat.copy(estaSortejant = false, valorTriat = (min..max).random())
        }
    }
}

data class EstatTriaNumero(
    val valorTriat : Int = 0,
    val estaSortejant : Boolean = false
)