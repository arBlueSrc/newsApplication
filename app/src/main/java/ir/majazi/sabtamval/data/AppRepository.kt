package ir.majazi.sabtamval.data

import com.example.global.data.Repository
import com.example.global.modules.app.model.AddProduct1
import com.example.global.modules.app.model.DetailScanner
import com.example.global.modules.app.model.Login
import com.example.global.network.ApiService
import com.example.global.network.resource.Resource
import com.example.global.network.resource.handleException
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService.App
) : Repository.App {


    override suspend fun login(userName: String, password: String): Resource<Login> {
        return try {
            val response = apiService.login(userName,password)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }

    override suspend fun detailScanner(userId: String): Resource<DetailScanner> {
        return try {
            val response = apiService.getDetailFromScanner(userId)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }

    override suspend fun getProduct1(): Resource<AddProduct1> {
        return try {
            val response = apiService.getProduct1()
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }
}