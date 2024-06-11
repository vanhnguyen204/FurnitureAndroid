package com.example.furniture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.furniture.data.viewmodel.ProductViewModel
import com.example.furniture.ui.screens.home.components.ProductCardSearch
import kotlinx.coroutines.launch

@Composable
fun ModalSearch(visible: Boolean,navController: NavController, productViewModel: ProductViewModel, onClose: () -> Unit) {
    var valueSearch by remember {
        mutableStateOf("")
    }
    val productsSearch by productViewModel.productsSearch.collectAsState()
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        placeholder = {
                            Text(
                                text = "Search product...", style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            )
                        },
                        modifier = Modifier.weight(1f),
                        value = valueSearch,
                        onValueChange = {
                            valueSearch = it
                            productViewModel.viewModelScope.launch {
                                productViewModel.searchProductByName(it)
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                    )
                    Text(text = "Cancel", style = TextStyle(
                        fontSize = 14.sp
                    ), modifier = Modifier
                        .clickable {
                            onClose()
                        }
                        .padding(end = 10.dp)
                    )
                }
                if (productsSearch.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Not found product $valueSearch")
                    }
                } else {
                    LazyColumn {
                        items(productsSearch) {
                            ProductCardSearch(item = it, navController = navController, onClose = onClose)
                        }
                    }
                }
            }
        }

    }
}