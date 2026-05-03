package com.example.apiretrofitdaggerhiltdicoilproject.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.product.component.ProductDetailsForm
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.product.component.ProductList
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.productState.UiState
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductManager(viewModel : ProductViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var showEditProduct by remember { mutableStateOf(false) }
    var showAddProduct by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<ProductModel?>(null) }




    LaunchedEffect(message) {
        if(message.isNotBlank()){
            snackbarHostState.showSnackbar(message)
            viewModel.clearMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Manager") },
                actions = {
                    IconButton(onClick = { viewModel.loadAllProducts() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
                    }
                    IconButton(onClick = { showAddProduct = true }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add product")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding->

        when(val state = uiState){
            is UiState.Loading -> {
                Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                ProductList(
                    modifier = Modifier.padding(innerPadding),
                    product = state.products,
                    onEdit = { product->
                        showEditProduct=true
                        selectedProduct=product
                    },
                    onPatch = {
                        viewModel.patchProduct(it.id,"${it.title} (Favourite)")
                    },
                    onDelete = {
                        viewModel.deleteProduct(it.id)
                    }
                )

            }
            is UiState.Error -> {
                Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "Error: ${state.message}")
                }
            }


        }

    }

    if(showAddProduct){
        ProductDetailsForm(
            title = "add product",
            product = null,
            onDismiss = {
                showAddProduct = false
            },
            onSave = { product->
                viewModel.createProducts(product)
                showAddProduct = false
            }
        )
    }

    if(showEditProduct){
        ProductDetailsForm(
            title = "edit product",
            product = selectedProduct,
            onDismiss = {
                showEditProduct = false
                selectedProduct = null
            },
            onSave = { product->
                selectedProduct?.let {
                    viewModel.updateProduct(it.id,product)

                }
                showEditProduct= false
                selectedProduct=null
            }
        )
    }

}