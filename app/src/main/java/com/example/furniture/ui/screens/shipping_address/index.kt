package com.example.furniture.ui.screens.shipping_address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.viewmodel.ShippingAddressViewModel
import com.example.furniture.helper.Console
import com.example.furniture.helper.Resource
import com.example.furniture.ui.screens.shipping_address.components.ShippingAddressItem
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils

@Composable
fun ShippingAddressScreen(
    navHostController: NavHostController,
    shippingAddressViewModel: ShippingAddressViewModel = hiltViewModel<ShippingAddressViewModel>()
) {
    val shippingAddress by shippingAddressViewModel.shippingAddresses.collectAsState()
    LaunchedEffect(key1 = Unit) {
        shippingAddressViewModel.getMyShippingAddress()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.appColors.tertiary)
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(text = "Shipping Address", style = AppTheme.appTypography.titleHeaderStyle)
                },
                sizeIconLeft = 30.dp,
                sizeIconRight = null,
                iconLeftPress = {
                    navHostController.popBackStack()
                    Unit
                },
                iconRightPress = { },
                modifier = Modifier.fillMaxWidth()
            )

            if (shippingAddress.isEmpty()) {
                 Box(
                   contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "You haven't any shipping addresses.")
                }

            }else{

                LazyColumn {
                    itemsIndexed(shippingAddress) { index, item ->
                        ShippingAddressItem(
                            item = item,
                            index = index,
                            shippingAddressViewModel,
                            navHostController
                        )
                    }
                }
            }

        }
        SmallFloatingActionButton(onClick = {
            navHostController.navigate(NavigationUtils.shippingAddressManage + "/true/null")
        }, modifier = Modifier.offset()) {
            Icon(
                painter = painterResource(id = R.drawable.plus_child),
                contentDescription = "Plus",
                modifier = Modifier.size(20.dp)
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingAddressItemPreview() {
    AppTheme {
        val navHostController = rememberNavController()
        ShippingAddressScreen(navHostController = navHostController)
    }
}