package com.example.apiretrofitdaggerhiltdicoilproject.data.remoteapi

import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("/products")      // get all products
    suspend fun getAllProducts(): List<ProductModel>

    @GET("/products/{id}")   // get single product
    suspend fun getProduct(@Path("id") id: Int): ProductModel

    @POST("/products")    // create new product
    suspend fun createProduct(@Body product: ProductModel) : ProductModel

    @PUT("/products/{id}")   //  🔄 Update entire data (Replaces full resource)
    suspend fun updateProduct(
        @Path("id") id : Int,
        @Body product : ProductModel
        ) : ProductModel

    @PATCH("/products/{id}")   // update specific data of a product
    suspend fun patchProduct(
        @Path("id") id : Int,
        @Body updates : Map<String , @JvmSuppressWildcards Any>
        ) : ProductModel

    @DELETE("/products/{id}")   // delete a product
    suspend fun deleteProduct(@Path("id") id : Int) : ProductModel


}