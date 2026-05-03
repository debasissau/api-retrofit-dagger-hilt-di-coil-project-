package com.example.apiretrofitdaggerhiltdicoilproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.product.ProductManager
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.theme.ApiRetrofitDaggerHiltDICoilProjectTheme
import com.example.apiretrofitdaggerhiltdicoilproject.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        setContent {
            ApiRetrofitDaggerHiltDICoilProjectTheme {
                Surface() {
                    ProductManager(viewModel)
                }
            }
        }
    }
}
