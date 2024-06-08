package com.example.furniture.ui.screens.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniture.R
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils


@Composable
fun ShippingItemCheckout(item: ShippingAddress, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shipping address",
                style = AppTheme.appTypography.textProduct.copy(fontSize = 18.sp)
            )
            Image(
                painter = painterResource(id = R.drawable.pen_icon),
                contentDescription = "Icon pen",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.navigate(NavigationUtils.shippingAddress)

                    }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.recipient,
                    style = AppTheme.appTypography.text18Nunitosan,
                    modifier = Modifier.padding(10.dp)
                )

            }
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.appColors.appGray)
            )
            Text(
                text = item.addressDetail + ", " + item.district + ", " + item.city + ", " + item.country,
                style = AppTheme.appTypography.textProduct,
                modifier = Modifier.padding(15.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}