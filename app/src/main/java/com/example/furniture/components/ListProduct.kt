package com.example.furniture.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.Product
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.RetrofitUtils
import com.google.gson.Gson



@Composable
fun ListProduct(data: List<Product>, navHostController: NavHostController) {
    val listState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 20.dp
    ) {
        itemsIndexed(data) { index: Int, item: Product ->
            ProductItemShow(index = index, item = item, navHostController)
        }
    }

}
val gson = Gson();
@Composable
fun ProductItemShow(index: Int, item: Product, navHostController: NavHostController) {

    Column {

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.clip(RoundedCornerShape(20.dp)).fillMaxSize(),
        ) {
            AsyncImage(model = RetrofitUtils.BASE_URL + item.image,
                contentDescription = "Product image ${index}",
                contentScale = ContentScale.Fit ,
                )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp, end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0, 0, 0, 40))
                        .padding(5.dp)
                        .clickable {
                            navHostController.navigate(NavigationUtils.productDetails + "/" + item.id)
                        }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag),
                        contentDescription = "Shopping bag $index",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            }

        }


        Text(
            text = item.name,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            style = AppTheme.appTypography.textProduct
        )
        Text(text = "$ ${item.price}", style = AppTheme.appTypography.priceProduct)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListProductPreview() {
    AppTheme {
//        val navHostController = rememberNavController();
//        val item1 = Product("819dja","Coffee Chair", 20, R.drawable.coffee_chair, "Description ...")
//        val item2 = Product("1231","Coffee Chair", 20, R.drawable.table, "Description ...")
//        val item3 = Product("12311","Coffee Chair", 20, R.drawable.table_desk, "Description ...")
//        val item4 = Product("12333","Coffee Chair", 20, R.drawable.lamp_product, "Description ...")
//        val listItem = listOf(item1, item2, item3, item4)
//        ListProduct(data = listItem, navHostController)
    }
}