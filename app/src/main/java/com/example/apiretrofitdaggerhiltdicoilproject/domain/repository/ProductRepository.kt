package com.example.apiretrofitdaggerhiltdicoilproject.domain.repository

import com.example.apiretrofitdaggerhiltdicoilproject.data.remoteapi.ApiService
import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel
import javax.inject.Inject

class ProductRepository @Inject constructor( private val api : ApiService ) {

    suspend fun getAllProducts() = api.getAllProducts()

    suspend fun getProduct(id : Int) = api.getProduct(id)

    suspend fun createNewProduct(product : ProductModel) = api.createProduct(product)

    suspend fun updateProduct(id : Int,product : ProductModel) = api.updateProduct(id,product)

    suspend fun patchProduct(id: Int, updates: Map<String, Any>) = api.patchProduct(id,updates)

    suspend fun deleteProduct(id: Int) = api.deleteProduct(id)


}