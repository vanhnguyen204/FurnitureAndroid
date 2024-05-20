package com.example.furniture.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.CategoryComponent
import com.example.furniture.components.Header
import com.example.furniture.components.ListProduct
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.data.viewmodel.ProductViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, productViewModel: ProductViewModel = hiltViewModel<ProductViewModel>()) {
    val products by productViewModel.products.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = R.drawable.cart,
            contentCenter = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Make home", style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Gray,
                            fontFamily = FontFamily(AppTheme.appFonts.gelasioRegular),
                            fontWeight = FontWeight(400)
                        )
                    )
                    Text(
                        text = "Beautiful", style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(AppTheme.appFonts.gelasioRegular),
                            fontWeight = FontWeight(700)
                        )
                    )
                }
                /*TODO*/
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 27.dp,
            iconLeftPress = { /*TODO*/ },
            iconRightPress = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        CategoryComponent()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (products.isNotEmpty()) {
            ListProduct(data = products, navHostController)
            } else {
               Text(text = "Loading...")

            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    val image: Int = R.drawable.background;
    val navHostController = rememberNavController();
    val productViewModel: ProductViewModel = viewModel<ProductViewModel>()
    AppTheme {

    }
}