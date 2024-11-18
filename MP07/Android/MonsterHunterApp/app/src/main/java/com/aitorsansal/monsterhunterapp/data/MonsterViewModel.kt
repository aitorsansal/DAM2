import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitorsansal.monsterhunterapp.model.Monster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonsterViewModel : ViewModel() {
    private val _monsterData = MutableStateFlow<List<Monster>>(emptyList())
    val MonsterData: StateFlow<List<Monster>> = _monsterData

    fun setMonsters(monsters: List<Monster>) {        _monsterData.value = monsters
    }
}