package ir.majazi.sabtamval.data

import com.example.global.data.Repository
import com.example.global.modules.setting.model.OtherLink
import com.example.global.modules.setting.model.ProfileUser
import com.example.global.modules.setting.model.SendTicket
import com.example.global.network.ApiService
import com.example.global.network.resource.Resource
import com.example.global.network.resource.handleException
import javax.inject.Inject

//class AppRepository @Inject constructor(
//    private val apiService: ApiService.Setting
//) : Repository.Setting {


//    override suspend fun getProfileData(mobile: String): Resource<ProfileUser> {
//        return try {
//            val response = apiService.getProfileData("application/json", mobile)
//            Resource.Success(data = response)
//        } catch (e: java.lang.Exception) {
//            handleException(e)
//        }
//    }

//}