package ir.majazi.sabtamval.ui.addGood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.AddProduct1
import com.example.global.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majazi.sabtamval.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGoodViewModel @Inject constructor(
    private val addProductRepository: AppRepository
):ViewModel(){

    private val _responseAddProduct = MutableStateFlow<Resource<AddProduct1>?>(null)
    val responseLogin
        get() = _responseAddProduct.asStateFlow()

    fun addProduct() {
        viewModelScope.launch {
            _responseAddProduct.update {
                addProductRepository.getProduct1()

            }
        }
    }
}