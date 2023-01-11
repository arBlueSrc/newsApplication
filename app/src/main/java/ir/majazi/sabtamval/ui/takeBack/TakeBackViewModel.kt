package ir.majazi.sabtamval.ui.takeBack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.DetailScanner
import com.example.global.modules.app.model.EditProudect
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeBackViewModel @Inject constructor(private val scannerRepository: AppRepository) : ViewModel() {

    private val _responseTakeBack = MutableStateFlow<Resource<EditProudect>?>(null)
    val responseTakeBack
        get() = _responseTakeBack.asStateFlow()

    fun takeBack(productId:String, description:String) {
        viewModelScope.launch {
            _responseTakeBack.update {
                scannerRepository.takeBack(productId,description)
            }
        }
    }

}