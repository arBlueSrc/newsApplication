package ir.majazi.sabtamval.ui.trust

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.Lend
import com.example.global.modules.app.model.Login
import com.example.global.modules.app.model.Person
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrustViewModel  @Inject constructor(
    private val getPersonRepository: AppRepository
):ViewModel(){



    private val _responsePerson = MutableStateFlow<Resource<Person>?>(null)
    val responseLogin
        get() = _responsePerson.asStateFlow()

    fun getPerson() {
        viewModelScope.launch {
            _responsePerson.update {
                getPersonRepository.getPerson()
            }
        }
    }

    private val _responseLend = MutableStateFlow<Resource<Lend>?>(null)
    val responseLend
        get() = _responseLend.asStateFlow()

    fun lend(product:String,borrowerId:String) {
        viewModelScope.launch {
            _responseLend.update {
                getPersonRepository.lend(product,borrowerId)
            }
        }
    }

}