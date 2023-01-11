package ir.majazi.sabtamval.ui.confirmInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.AddProduct1
import com.example.global.modules.app.model.AddProductResult
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmInformationViewModel @Inject constructor(
    private val addProductRepository: AppRepository
):ViewModel(){
    private val _responseAddProduct = MutableStateFlow<Resource<AddProductResult>?>(null)
    val responseLogin
        get() = _responseAddProduct.asStateFlow()

    fun addProduct(goodId:String,storeId:String,partId:String,employeeId:String,goodProperty:String) {
        viewModelScope.launch {
            _responseAddProduct.update {
                addProductRepository.addProductResult(goodId,storeId, partId, employeeId, goodProperty)
            }
        }
    }
}