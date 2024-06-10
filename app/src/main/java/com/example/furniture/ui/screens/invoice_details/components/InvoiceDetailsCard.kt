package com.example.furniture.ui.screens.invoice_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.furniture.data.model.response.Cart
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils

@Composable
fun InvoiceDetailsCart(item: Cart, onRateClick: (i: Cart) -> Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(
                    model = RetrofitUtils.BASE_URL + item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    Text(text = item.name, style = AppTheme.appTypography.textProduct)
                    Text(
                        text = "$ ${item.price}",
                        style = AppTheme.appTypography.priceProduct,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    Text(text = "Quantity: ${item.quantity}")
                }
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppTheme.appColors.appGray)
                    .padding(7.dp)
                    .background(AppTheme.appColors.appGray)
                    .padding(horizontal = 8.dp)
                    .clickable {
                        onRateClick(item)
                    }
            ) {
                Text(
                    text = "Rate", style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}