package com.example.furniture.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.CategoryComponent
import com.example.furniture.components.Header
import com.example.furniture.components.ListProduct
import com.example.furniture.components.ModalSearch
import com.example.furniture.constant.Storage
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.data.viewmodel.ProductViewModel
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.utils.NavigationUtils
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel<ProductViewModel>()
) {
    val products by productViewModel.products.collectAsState()
    val context = LocalContext.current
    var isSearch by remember {
        mutableStateOf(false)
    }
    val getToken =
        SharedPreferencesHelper(context).getDataLocalStorage(Storage.TOKEN.toString(), "") ?: ""
    Box(modifier = Modifier.fillMaxSize()) {

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
                },
                sizeIconLeft = 30.dp,
                sizeIconRight = 27.dp,
                iconLeftPress = {
                    isSearch = !isSearch
                    Unit
                },
                iconRightPress = {
                    navHostController.navigate(NavigationUtils.cart)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            CategoryComponent(onItemSelected = {
                productViewModel.viewModelScope.launch {
                    productViewModel.getProductOfCategory("Bear $getToken", it.value)
                }
            })

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (products.isNotEmpty()) {
                    ListProduct(data = products, navHostController)
                } else {
                    Text(text = "This category hasn't products")

                }

            }
        }
        ModalSearch(
            visible = isSearch,
            navController = navHostController,
            productViewModel = productViewModel
        ) {
            isSearch = !isSearch
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