package ir.majazi.sabtamval.ui.addSpecification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.AddProduct2
import com.example.global.modules.app.model.Login
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSpecificationsViewModel @Inject constructor(
    private val addGoodRepository: AppRepository
):ViewModel(){


    private val _responseAddGood = MutableStateFlow<Resource<AddProduct2>?>(null)
    val responseLogin
        get() = _responseAddGood.asStateFlow()

    fun addProduct2(goodId:String) {
        viewModelScope.launch {
            _responseAddGood.update {
                addGoodRepository.addProduct2(goodId)
            }
        }
    }
}