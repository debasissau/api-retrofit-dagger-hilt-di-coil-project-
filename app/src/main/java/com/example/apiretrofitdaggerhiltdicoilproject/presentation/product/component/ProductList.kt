package com.example.apiretrofitdaggerhiltdicoilproject.presentation.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel

@Composable
fun ProductList(
    product: List<ProductModel>,
    onEdit:(ProductModel)-> Unit,
    onDelete:(ProductModel)-> Unit,
    onPatch:(ProductModel)-> Unit,
    modifier: Modifier= Modifier
) {

    LazyColumn(modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(product, key = { it.id }) { item ->
            ProductCard(
                product = item,
                onEdit = { onEdit(item) },
                onDelete = { onDelete(item) },
                onPatch = { onPatch(item) }
            )
        }
    }
}