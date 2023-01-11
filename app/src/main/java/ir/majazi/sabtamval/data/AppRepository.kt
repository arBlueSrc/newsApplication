package ir.majazi.sabtamval.data

import com.example.global.data.Repository
import com.example.global.modules.app.model.*
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

    override suspend fun addProduct2(goodId: String): Resource<AddProduct2> {
        return try {
            val response = apiService.AddProduct2(goodId)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }


    override suspend fun addProductResult(
        goodId: String,
        storeId: String,
        partId: String,
        employeeId: String,
        propertyNumber: String,
        goodProperty: String
    ): Resource<AddProductResult> {
        return try {
            val response = apiService.addProductResult(goodId,storeId,partId,employeeId, propertyNumber,goodProperty)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }

    override suspend fun editProduct(
        productId: String,
        propertyNumber: String,
        goodProperty: String
    ): Resource<EditProudect> {
        return try {
            val response = apiService.editProduct(productId, propertyNumber, goodProperty)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }



    override suspend fun takeBack(productId: String, description: String): Resource<EditProudect> {
        return try {
            val response = apiService.takeBack(productId,description)
            Resource.Success(data = response)
        } catch (e: java.lang.Exception) {
            handleException(e)
        }
    }
}