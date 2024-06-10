package com.example.furniture.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.Payment
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.viewmodel.CartViewModel
import com.example.furniture.data.viewmodel.InvoiceViewModel
import com.example.furniture.data.viewmodel.PaymentViewModel
import com.example.furniture.data.viewmodel.ShippingAddressViewModel
import com.example.furniture.ui.screens.checkout.components.DeliveryMethod
import com.example.furniture.ui.screens.checkout.components.PaymentItemCheckOut
import com.example.furniture.ui.screens.checkout.components.ShippingItemCheckout
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

data class PriceType(
    val label: String,
    val price: Int
)

@Composable
fun Checkout(
    navController: NavHostController,
    invoiceViewModel: InvoiceViewModel = hiltViewModel<InvoiceViewModel>(),
    cartViewModel: CartViewModel = hiltViewModel<CartViewModel>(),
    shippingAddressViewModel: ShippingAddressViewModel = hiltViewModel<ShippingAddressViewModel>(),
    paymentViewModel: PaymentViewModel = hiltViewModel<PaymentViewModel>()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(0xFFF2F2F2),
    )
    val shippingAddress by shippingAddressViewModel.shippingAddresses.collectAsState()
    val payments by paymentViewModel.payments.collectAsState()
    val cart by cartViewModel.myCart.collectAsState()
    val order = PriceType("Order:", calculateTotalPrice(cart))
    val delivery = PriceType("Delivery: ", 5)
    val totalPrice = PriceType("Total: ", order.price + 5)
    val prices = listOf(order, delivery, totalPrice)
    val currentPayment = payments.find {
        it.isSelected
    } ?: Payment(
        "Unknown",
        "Unknown",
        "Unknown",
        "Unknown",
        0,
        "Unknown",
        true,
        "Unknown",
        "Unknown",
        "Unknown",
        )
    val currentShippingAddress = shippingAddress.find { it.isSelected } ?: ShippingAddress(
        "1",
        "1",
        "Unknown",
        "Unknown",
        "Unknown",
        "Unknown",
        "Unknown",
        true
    )
    var currentDeliveryMethod by remember {
        mutableStateOf("fast")
    }
    LaunchedEffect(Unit) {
        shippingAddressViewModel.getMyShippingAddress()
        paymentViewModel.getMyPayment()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(text = "Check out", style = AppTheme.appTypography.titleHeaderStyle)
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
            Spacer(modifier = Modifier.height(20.dp))
            ShippingItemCheckout(
                item = currentShippingAddress,
                navController = navController
            )

            Spacer(modifier = Modifier.height(30.dp))
            PaymentItemCheckOut(payment = currentPayment,
                navController = navController)
            Spacer(modifier = Modifier.height(30.dp))

            DeliveryMethod {
                currentDeliveryMethod = it.type
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                itemsIndexed(prices) { index, item ->
                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = if (index == 0) 10.dp else 0.dp,
                                    topStart = if (index == 0) 10.dp else 0.dp,
                                    bottomStart = if (index == prices.size - 1) 10.dp else 0.dp,
                                    bottomEnd = if (index == prices.size - 1) 10.dp else 0.dp,
                                )
                            )
                            .background(Color.White)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.label)
                        Text(text = "$ ${item.price}")
                    }
                }
            }
        }

        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            onClick = {
                invoiceViewModel.viewModelScope.launch {
                    val isCreated = invoiceViewModel.createInvoice(
                        RequestInvoice(
                            totalPrice = totalPrice.price,
                            paymentType = currentPayment.type,
                            shippingAddress = concatShippingAddress(currentShippingAddress),
                            delivery = currentDeliveryMethod,
                            data = cart
                        )
                    )
                    if (isCreated) {
                        navController.navigate(NavigationUtils.donePurchase)
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "SUBMIT ORDER",
                textAlign = TextAlign.Center,
                style = AppTheme.appTypography.largeTitle.copy(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    fontFamily = AppTheme.appFonts.nunitoSanRegular.toFontFamily()
                ),
            )
        }

    }
}

fun concatShippingAddress(i: ShippingAddress): String {
    return i.addressDetail +", "+i.district + ", "+i.city + ", "+i.country
}
fun calculateTotalPrice(list: List<Cart>): Int {
    var total = 0;
    list.forEach { item ->
        total += (item.quantity * item.price)
    }
    return total

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckoutPreview() {
    AppTheme {

    }
}