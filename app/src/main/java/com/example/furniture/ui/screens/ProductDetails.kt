package com.example.furniture.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProductDetails(navHostController: NavHostController, idProductItem: String?) {
    val pagerState = rememberLazyListState()
Text(text = "${idProductItem} Hello ae")
    Column {
        LazyRow {

        }
    }
}