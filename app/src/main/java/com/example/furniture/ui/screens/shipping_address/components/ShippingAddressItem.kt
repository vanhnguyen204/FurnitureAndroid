package com.example.furniture.ui.screens.shipping_address.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.furniture.R
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.viewmodel.ShippingAddressViewModel
import com.example.furniture.helper.Console
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.google.gson.Gson

@Composable
fun ShippingAddressItem(item: ShippingAddress, index: Int, shippingAddressViewModel: ShippingAddressViewModel, navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = item.isSelected, onCheckedChange = {
                    Console().log("ACTIVE ", "CLICK")
                                   shippingAddressViewModel.activeShippingAddress(item.id)
                }, colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black
                )
            )
            Text(
                text = "Use as the shipping address",
                style = AppTheme.appTypography.text18Nunitosan.copy(fontWeight = FontWeight(400))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.recipient, style = AppTheme.appTypography.text18Nunitosan)
                Image(
                    painter = painterResource(id = R.drawable.pen_icon),
                    contentDescription = "Icon pen $index",
                    modifier = Modifier.size(20.dp)
                        .clickable {
                            val itemJson = Gson().toJson(item)
                            navHostController.navigate(NavigationUtils.shippingAddressManage+"/false/$itemJson")
                        }
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
                modifier = Modifier.padding(10.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingAddressItemPreview() {
    AppTheme {

    }
}