package com.example.apiretrofitdaggerhiltdicoilproject.presentation.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.apiretrofitdaggerhiltdicoilproject.domain.model.ProductModel

@Composable
fun ProductDetailsForm(
    title: String,
    product: ProductModel?=null,
    onDismiss:()-> Unit,
    onSave:(ProductModel)-> Unit

) {
    var newtitle by remember { mutableStateOf(product?.title?:"") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var category by remember { mutableStateOf(product?.category ?: "") }
    var image by remember { mutableStateOf(product?.image ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    value = newtitle,
                    onValueChange = { newtitle = it },
                    label = { Text("Title") }
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") }
                )
                OutlinedTextField(
                    value = image,
                    onValueChange = { image = it },
                    label = { Text("Image URL") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val newProduct = ProductModel(
                    id=product?.id?:0,
                    title = newtitle,
                    price = price.toDoubleOrNull()?:0.0,
                    description = description,
                    category = category,
                    image = image
                )
                onSave(newProduct)
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}