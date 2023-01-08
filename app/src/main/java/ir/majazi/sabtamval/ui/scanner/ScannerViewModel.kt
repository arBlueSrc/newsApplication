package ir.majazi.sabtamval.ui.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.DetailScanner
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel  @Inject constructor(
    private val scannerRepository: AppRepository
):ViewModel(){

    private val _responseScanner = MutableStateFlow<Resource<DetailScanner>?>(null)
    val responseScanner
        get() = _responseScanner.asStateFlow()

    fun getResponseScanner(userId:String) {
        viewModelScope.launch {
            _responseScanner.update {
                scannerRepository.detailScanner(userId)
            }
        }
    }


}