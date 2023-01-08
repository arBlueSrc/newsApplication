package ir.majazi.sabtamval.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(
    private val loginRepository: AppRepository
):ViewModel(){



    private val _responseLogin = MutableStateFlow<Resource<Login>?>(null)
    val responseLogin
        get() = _responseLogin.asStateFlow()

     fun checkLogin(userName:String, password:String) {
        viewModelScope.launch {
            _responseLogin.update {
                loginRepository.login(userName, password)
            }
        }
    }

    fun emptyFiledUserName(userName: String):Boolean{
        return userName.isEmpty()
    }
    fun emptyFiledPassword(password: String):Boolean{
        return password.isEmpty()
    }
}