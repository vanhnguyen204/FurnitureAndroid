package com.example.furniture.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.CategoryComponent
import com.example.furniture.components.Header
import com.example.furniture.components.ListProduct
import com.example.furniture.components.ProductItem
import com.example.furniture.ui.theme.AppTheme

@Composable
fun HomeScreen(navHostController: NavHostController) {
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
        val item1 = ProductItem("Coffee Chair", 20, R.drawable.coffee_chair, "Description ...")
        val item2 = ProductItem("Coffee Chair", 20, R.drawable.table, "Description ...")
        val item3 = ProductItem("Coffee Chair", 20, R.drawable.table_desk, "Description ...")
        val item4 = ProductItem("Coffee Chair", 20, R.drawable.lamp_product, "Description ...")
        val item5 = ProductItem("Coffee Chair", 20, R.drawable.chair_colour, "Description ...")
        val item6 = ProductItem("Coffee Chair", 20, R.drawable.chair_single, "Description ...")
        val item7 = ProductItem("Coffee Chair", 20, R.drawable.sofa_luxury, "Description ...")
        val listItem = listOf(item1, item2, item3, item4, item6, item5, item7)
        Column(modifier = Modifier.fillMaxSize()) {
            ListProduct(data = listItem, navHostController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    val image: Int = R.drawable.background;
    val navHostController = rememberNavController();
    AppTheme {
        HomeScreen(navHostController = navHostController)
    }
}