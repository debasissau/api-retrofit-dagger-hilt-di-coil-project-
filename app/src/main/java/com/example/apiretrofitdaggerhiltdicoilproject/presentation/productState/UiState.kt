package com.example.apiretrofitdaggerhiltdicoilproject.presentation.productState

import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel

sealed class UiState {
    object Loading : UiState()
    data class Success(val products : List<ProductModel>) : UiState()
    data class Error(val message : String) : UiState()
}