package com.example.furniture.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furniture.components.Line
import com.example.furniture.data.model.response.Product
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.RetrofitUtils

@Composable
fun ProductCardSearch(item: Product, onClose: () -> Unit, navController: NavController) {
    Column(modifier = Modifier
        .padding(10.dp)
        .clickable {
            onClose()
            navController.navigate(NavigationUtils.productDetails + "/" + item.id)
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            AsyncImage(
                model = RetrofitUtils.BASE_URL + item.image,
                contentDescription = "Image product search",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(text = item.name, style = AppTheme.appTypography.textProduct)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$ ${item.price}", style = AppTheme.appTypography.priceProduct)
            }
        }
        Line()
    }
}