package com.example.furniture.ui.screens.checkout.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.Payment
import com.example.furniture.ui.screens.payment.components.renderCardNumber
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.RetrofitUtils

@Composable
fun PaymentItemCheckOut(payment: Payment, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()

        .padding(horizontal = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Payment",
                style = AppTheme.appTypography.textProduct.copy(fontSize = 18.sp)
            )
            Image(
                painter = painterResource(id = R.drawable.pen_icon),
                contentDescription = "Icon pen",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.navigate(NavigationUtils.payment)

                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))

                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = RetrofitUtils.BASE_URL + payment.image,
                contentDescription = "Icon bank",
                modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(2))
            )
            Text(
                text = renderCardNumber(payment.cartNumber),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}