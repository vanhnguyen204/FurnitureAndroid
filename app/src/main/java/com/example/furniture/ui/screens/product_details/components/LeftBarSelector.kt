package com.example.furniture.ui.screens.product_details.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.furniture.ui.screens.product_details.ProductDetails
import com.example.furniture.ui.theme.AppTheme

data class ProductColor(
    val backgroundColor: Color
)

@Composable
fun LeftBarSelector(currentPageIteration: Int, onClick: (index: Int) -> Unit) {
    val productColorFirst = ProductColor(Color.White)
    val productColorSecond = ProductColor(AppTheme.appColors.appBrown)
    val productColorThird = ProductColor(Color(0xFFE9CAA9))
    val listColor = listOf(productColorFirst, productColorSecond, productColorThird)
    LazyColumn {
        itemsIndexed(listColor) { index, item ->
            val colorBorder =
                if (currentPageIteration == index) Color(0xFF909090) else Color(0xFFF0F0F0)

            Surface(
                border = BorderStroke(5.dp, colorBorder),
                shape = CircleShape,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clickable {
                        onClick(index)
                    }) {
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .size(30.dp)
                        .background(item.backgroundColor)

                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ViewPagerItemPreview() {
    val navHostController = rememberNavController()

}