package com.example.furniture.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.viewmodel.CartViewModel
import com.example.furniture.ui.screens.cart.components.CartItem
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.google.gson.Gson

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel<CartViewModel>()
) {
    val carts by cartViewModel.myCart.collectAsState()
    var totalPriceState by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = carts) {
        totalPriceState = calTotalPrice(carts)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(text = "My Cart", style = AppTheme.appTypography.titleHeaderStyle)
                },
                sizeIconLeft = 30.dp,
                sizeIconRight = 30.dp,
                iconLeftPress = {
                    navController.popBackStack()
                    Unit
                },
                iconRightPress = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
            if (carts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Your cart is empty.")
                }
                return
            }
            LazyColumn {
                items(carts) {
                    CartItem(item = it)
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 10.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TextField(
//                    value = "", onValueChange = {},
//                    modifier = Modifier
//                        .weight(1f)
//                        .height(45.dp)
//                        .shadow(10.dp, shape = RoundedCornerShape(20.dp)
//                            , spotColor = Color.Gray),
//                    placeholder = {
//                        Text(
//                            text = "Enter your promo code", style = TextStyle(
//                                color = Color.Gray
//                            )
//                        )
//                    },
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = Color.White,
//                        unfocusedContainerColor = Color.White,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ),
//                    shape = RoundedCornerShape(10.dp),
//
//                    trailingIcon = {
//                        Button(
//                            onClick = { /*TODO*/ },
//                            shape = RoundedCornerShape(10.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color.Black
//                            ),
//                            modifier = Modifier.fillMaxHeight()
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.arrow_right),
//                                contentDescription = "icon",
//                                modifier = Modifier.size(15.dp)
//                            )
//                        }
//                    }
//                )
//
//            }
//            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total price",
                    style = AppTheme.appTypography.textProduct.copy(fontSize = 20.sp)
                )
                Text(
                    text = "$ $totalPriceState",
                    style = AppTheme.appTypography.priceProduct.copy(fontSize = 20.sp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                          navController.navigate(NavigationUtils.checkout)
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Check out",
                    textAlign = TextAlign.Center,
                    style = AppTheme.appTypography.largeTitle.copy(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight(600),
                        fontFamily = AppTheme.appFonts.nunitoSanRegular.toFontFamily()
                    ),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

    }
}

fun calTotalPrice(carts: List<Cart>): Int {
    var totalPrice = 0
    carts.forEach { cart ->
        totalPrice += (cart.quantity * cart.price)

    }
    return totalPrice
}