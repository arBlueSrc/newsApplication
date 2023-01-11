package ir.majazi.sabtamval.ui.EditSpecification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.global.modules.app.model.AddProductResult
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
class EditSpecificationsViewModel @Inject constructor(
    private val editProductRepository: AppRepository
): ViewModel(){
    private val _responseEditProduct = MutableStateFlow<Resource<EditProudect>?>(null)
    val responseLogin
        get() = _responseEditProduct.asStateFlow()

    fun editProduct(productId:String,propertyNumber:String,goodProperty:String) {
        viewModelScope.launch {
            _responseEditProduct.update {
                editProductRepository.editProduct(productId,propertyNumber ,goodProperty)
            }
        }
    }
}